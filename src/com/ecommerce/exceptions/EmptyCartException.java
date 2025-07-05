package com.ecommerce.exceptions;

/**
 * Exception thrown when an operation is attempted on an empty shopping cart.
 */
public class EmptyCartException extends ECommerceException {
    /**
     * Constructs a new EmptyCartException with the specified detail message.
     *
     * @param message the detail message
     */
    public EmptyCartException(String message) {
        super(message);
    }
}
