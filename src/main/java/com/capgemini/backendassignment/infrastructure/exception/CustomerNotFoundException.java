package com.capgemini.backendassignment.infrastructure.exception;

public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException(final String message) {
        super(message);
    }
}
