package com.ecommerce.model.product;

import java.util.Objects;

public class BasicProduct implements Product {
    private final String name;
    private final double price;
    private int quantity;

    /**
     * Constructor for the BasicProduct class.
     * @param name The name of the product.
     * @param price The price of the product.
     * @param quantity The quantity of the product in stock.
     * @throws IllegalArgumentException if name is null or empty, or if price or quantity are negative.
     */
    public BasicProduct(String name, double price, int quantity) {
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

    @Override public String getName() { return name; }
    @Override public double getPrice() { return price; }
    @Override public int getQuantity() { return quantity; }
    @Override public void setQuantity(int quantity) { this.quantity = quantity; }

     /**
     * Returns a string representation of the product.
     * @return A string containing the product's name and price.
     */
    public String toString() {
        return "Product{name='" + name + "', price=" + price + "}";
    }

    /**
     * Checks if two products are equal based on their name, price, and quantity.
     * @param obj The object to compare with this product.
     * @return true if the products are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BasicProduct product = (BasicProduct) obj;
        return Double.compare(product.price, price) == 0 &&
        quantity == product.quantity 
        && Objects.equals(name, product.name);
    }

    /**
     * Returns a hash code for the product based on its name, price, and quantity.
     * @return A hash code value for this product.
     */
    public int hashCode() {
        return Objects.hash(name, price, quantity);
    }

}
