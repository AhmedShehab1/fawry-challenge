package com.ecommerce.exceptions;

/**
 * Exception thrown when a product is no longer available for purchase.
 */
public class ExpiredProductException extends ECommerceException {
    /**
     * Constructs a new ExpiredProductException with the specified detail message.
     *
     * @param message the detail message
     */
    public ExpiredProductException(String message) {
        super(message);
    }
}
