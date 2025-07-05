package com.ecommerce.model.product;

import java.util.Objects;
/**
 * Abstract class representing a product in an inventory system.
 * It contains common attributes and methods for all products.
 */
public abstract class Product {
    protected String name;
    protected double price;
    protected int quantity;

    /**
     * Constructor for the Product class.
     * @param name The name of the product.
     * @param price The price of the product.
     * @param quantity The quantity of the product in stock.
     * @throws IllegalArgumentException if name is null or empty, or if price or quantity are negative.
     */
    public Product(String name, double price, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.price = price;
        this.quantity = quantity;
    }



    public String getName() {
        return name;
    }

    /**
     * Gets the price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the quantity of the product.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product.
     * @param quantity the new quantity of the product.
     * @throws IllegalArgumentException if quantity is negative.
     */
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    /**
     * Displays information about the product.
     * This method should be implemented by subclasses to provide specific details.
     */
    // public abstract void displayInfo();

    /**
     * Returns a string representation of the product.
     * @return A string containing the product's name and price.
     */
    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + "}";
    }

    /**
     * Checks if two products are equal based on their name, price, and quantity.
     * @param obj The object to compare with this product.
     * @return true if the products are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return Double.compare(product.price, price) == 0 &&
        quantity == product.quantity 
        && Objects.equals(name, product.name);
    }

    /**
     * Returns a hash code for the product based on its name, price, and quantity.
     * @return A hash code value for this product.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity);
    }

    /**
     * Abstract method to check if the product is expired.
     * This method should be implemented by subclasses that have expiration logic.
     * @return true if the product is expired, false otherwise.
     */
    public abstract boolean isExpired();
    /**
     * Abstract method to check if the product requires shipping.
     * This method should be implemented by subclasses that have specific shipping requirements.
     * @return true if the product requires shipping, false otherwise.
     */
    public abstract boolean requiresShipping();
}