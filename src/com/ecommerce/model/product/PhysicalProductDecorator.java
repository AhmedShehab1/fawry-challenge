package com.ecommerce.model.product;

import java.util.Objects;

public class PhysicalProductDecorator implements Shippable {
    private final double weight;
    private final Product product;

    public PhysicalProductDecorator(Product product, double weight) {
        this.product = Objects.requireNonNull(product, "Product cannot be null");
        
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
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
}
