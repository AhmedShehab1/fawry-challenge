package com.ecommerce.model.product;

public class PhysicalProductDecorator extends Product implements Shippable {
    private final double weight;

    public  PhysicalProductDecorator(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean requiresShipping() {
        return true; // Physical products require shipping
    }

    @Override
    public boolean isExpired() {
        return false; // Physical products are not perishable
    }
}
