package com.ecommerce.exceptions;

/**
 * Exception thrown when there is insufficient stock for a product.
 */
public class InsufficientStockException extends ECommerceException {
    /**
     * Constructs a new InsufficientStockException with the specified detail message.
     *
     * @param message the detail message
     */
    public InsufficientStockException(String message) {
        super(message);
    }
}
