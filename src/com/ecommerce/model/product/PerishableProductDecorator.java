package com.ecommerce.model.product;

import java.time.LocalDate;

public class PerishableProductDecorator implements Product, Expirable, Shippable {
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

    @Override public double getWeight() {
        if (product instanceof Shippable) {
            return ((Shippable) product).getWeight();
        }
        return 0.0;
    }
}