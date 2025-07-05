package com.ecommerce.model.product;

import java.time.LocalDate;

public class PerishableProductDecorator implements Product, Expirable {
    private final Product product;
    private final LocalDate expiryDate;

    public PerishableProductDecorator(Product product, LocalDate expiryDate) {
        this.product = product;
        this.expiryDate = expiryDate;
    }

    @Override public boolean isExpired() { 
        return LocalDate.now().isAfter(expiryDate); 
    }

    public String getName() {
        return product.getName();
    }
    public double getPrice() {
        return product.getPrice();
    }
    public int getQuantity() {
        return product.getQuantity();
    }
    public void setQuantity(int quantity) {
        product.setQuantity(quantity);
    }
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}