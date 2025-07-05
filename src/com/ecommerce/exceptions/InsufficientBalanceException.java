package com.ecommerce.exceptions;

/**
 * Exception thrown when a customer attempts to make a purchase
 * but does not have sufficient balance in their account.
 */
public class InsufficientBalanceException extends ECommerceException {
    /**
     * Constructs a new InsufficientBalanceException with the specified detail message.
     *
     * @param message the detail message
     */
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
