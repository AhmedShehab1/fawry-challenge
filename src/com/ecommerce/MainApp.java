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
        // Setup services
        ShippingService shippingService = new ConsoleShippingService();
        
        ShoppingCart cart = new ShoppingCart(shippingService);
        
        // Create products
        Product tv = new PhysicalProductDecorator(
            new BasicProduct("TV", 899.99, 5), 25.0
        );
        
        Product cheese = new PerishableProductDecorator(
            new PhysicalProductDecorator(
                new BasicProduct("Cheese", 8.99, 20),
                0.5
            ),
            LocalDate.now().plusDays(14)
        );
        
        // Create customer
        Customer customer = new Customer("Alice", 1000.00, cart);
        
        // Add items to cart
        cart.addItem(tv, 1);
        cart.addItem(cheese, 3);
        
        // Checkout
        try {
            customer.checkout();
        } catch (ECommerceException e) {
            System.err.println("Checkout failed: " + e.getMessage());
        }
    }
}
