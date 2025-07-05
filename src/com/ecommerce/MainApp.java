package com.ecommerce;

import java.time.LocalDate;

import com.ecommerce.model.product.BasicProduct;
import com.ecommerce.model.product.PerishableProductDecorator;
import com.ecommerce.model.product.PhysicalProductDecorator;
import com.ecommerce.exceptions.ECommerceException;
import com.ecommerce.model.product.Product;
import com.ecommerce.cart.ShoppingCart;
import com.ecommerce.model.Customer;
import com.ecommerce.shipping.ConsoleShippingService;
import com.ecommerce.shipping.ShippingService;

class Main {
    public static void main(String[] args) {
 
        // Create products
        Product cheese = new PerishableProductDecorator(
            new PhysicalProductDecorator(
                new BasicProduct("Cheese", 100, 10),
                0.4
            ),
            LocalDate.now().plusDays(10)
        );

        Product biscuits = new PerishableProductDecorator(
            new PhysicalProductDecorator(
                new BasicProduct("biscuits", 150, 5),
                0.7
            ),
            LocalDate.now().plusDays(20)
        );

        Product scratchCard = new BasicProduct("Scratch Card", 50, 100);
        
        // Create services
        ShippingService shippingService = new ConsoleShippingService();
        ShoppingCart cart = new ShoppingCart(shippingService);
        
        // Create customer
        Customer customer = new Customer("John", 500, cart);
        
        // Add items
        cart.addItem(cheese, 2);
        cart.addItem(biscuits, 1);
        cart.addItem(scratchCard, 1);
        
        try {
            customer.checkout();
        } catch (ECommerceException e) {
            System.err.println("Checkout failed: " + e.getMessage());
        }
    }
}
