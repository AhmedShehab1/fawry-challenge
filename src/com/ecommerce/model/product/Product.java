package com.ecommerce.model.product;

/**
 * Abstract class representing a product in an inventory system.
 * It contains common attributes and methods for all products.
 */
public interface Product {

    String getName();
    double getPrice();
    int getQuantity();
    void setQuantity(int quantity);

}
