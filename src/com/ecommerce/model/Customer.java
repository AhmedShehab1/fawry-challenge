package com.ecommerce.model;

import com.ecommerce.model.product.Product;
import com.ecommerce.cart.ShoppingCart;

public class Customer {
    private final String name;
    private double balance;
    private final ShoppingCart cart;

    public Customer(String name, double balance, ShoppingCart cart) {
        this.name = name;
        this.balance = balance;
        this.cart = cart;
    }

    public void checkout() {
        cart.checkout(this);
    }
    
    public void deductBalance(double amount) {
        this.balance -= amount;
    }
    
    
    public double getBalance() { return balance; }
    public String getName() {
        return name;
    }
    public ShoppingCart getCart() {
        return cart;
    }
}