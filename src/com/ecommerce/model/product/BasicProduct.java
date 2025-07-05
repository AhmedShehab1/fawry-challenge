package com.ecommerce.model.product;

public class BasicProduct extends Product {
    private String description;

    /**
     * Constructor for the BasicProduct class.
     * @param name The name of the product.
     * @param price The price of the product.
     * @param quantity The quantity of the product in stock.
     * @param description A brief description of the product.
     * @throws IllegalArgumentException if name is null or empty, or if price or quantity are negative.
     */
    public BasicProduct(String name, double price, int quantity) {
        super(name, price, quantity);
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
    }
}
