
package com.ecommerce.cart;

import com.ecommerce.model.Customer;
import com.ecommerce.model.product.Expirable;
import com.ecommerce.model.product.Product;
import com.ecommerce.model.product.Shippable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ecommerce.exceptions.EmptyCartException;
import com.ecommerce.exceptions.ExpiredProductException;
import com.ecommerce.exceptions.InsufficientBalanceException;
import com.ecommerce.exceptions.InsufficientStockException;
import com.ecommerce.shipping.ShippingService;

public class ShoppingCart {
    private final Map<Product, Integer> items = new HashMap<>();
    private final ShippingService shippingService;
    private static final double FLAT_SHIPPING_FEE = 30.0;

    public ShoppingCart(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public double calculateSubtotal() {
        return items.entrySet().stream()
            .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
            .sum();
    }
    
    public void addItem(Product product, int quantity) {
        if (product.getQuantity() < quantity) {
            throw new InsufficientStockException(
                "Not enough stock for " + product.getName());
        }
        items.merge(product, quantity, Integer::sum);
    }

    public void updateStock() {
        items.forEach((product, quantity) -> {
            int newQuantity = product.getQuantity() - quantity;
            product.setQuantity(newQuantity);
        });
        items.clear();
    }

    public void processPayment(Customer customer, double total) {
        customer.deductBalance(total);
    }

    public void checkout(Customer customer) {
        validateCheckout(customer);
        double subtotal = calculateSubtotal();
        double shipping = calculateShipping();
        double total = subtotal + shipping;
        
        processPayment(customer, total);
        shipShippableItems();
        printReceipt(subtotal, shipping, total, customer.getBalance());
        updateStock();
    }

    private void validateCheckout(Customer customer) {
        if (items.isEmpty()) throw new EmptyCartException("Cart is empty");
        
        items.forEach((product, quantity) -> {
            if (product.getQuantity() < quantity) {
                throw new InsufficientStockException(
                    product.getName() + " is out of stock");
            }
            if (product instanceof Expirable expirable && expirable.isExpired()) {
                throw new ExpiredProductException(
                    product.getName() + " is expired");
            }
        });
        
        double totalCost = calculateSubtotal() + calculateShipping();
        if (customer.getBalance() < totalCost) {
            throw new InsufficientBalanceException(
                "Insufficient funds. Need: " + totalCost);
        }
    }

    private double calculateShipping() {
        return items.keySet().stream()
            .anyMatch(p -> p instanceof Shippable) ? FLAT_SHIPPING_FEE : 0;
    }

    private void shipShippableItems() {
        Map<Shippable, Integer> shippableItems = new HashMap<>();
    
        items.forEach((product, quantity) -> {
            if (product instanceof Shippable shippable) {
                shippableItems.put(shippable, quantity);
            }
        });
        
        if (!shippableItems.isEmpty()) {
            shippingService.shipItems(shippableItems);
        }
    }

    private void printReceipt(double subtotal, double shipping, 
                             double total, double newBalance) {
        System.out.println("** Checkout receipt **");
        items.forEach((product, quantity) -> 
            System.out.printf("%dx %s    %.0f%n", 
                quantity, product.getName(), product.getPrice() * quantity));
        
        System.out.println("---");
        System.out.printf("Subtotal    %.0f%n", subtotal);
        System.out.printf("Shipping    %.0f%n", shipping);
        System.out.printf("Amount    %.0f%n", total);
        System.out.printf("New balance: %.0f%n", newBalance);
    }
}
