package com.ecommerce.exceptions;

/**
 * Custom exception class for handling e-commerce related errors.
 * This class extends RuntimeException, allowing it to be thrown
 * without being declared in method signatures.
 */
public class ECommerceException extends RuntimeException {
    /**
     * Constructs a new ECommerceException with the specified detail message.
     *
     * @param message the detail message
     */
    public ECommerceException(String message) {
        super(message);
    }
}
