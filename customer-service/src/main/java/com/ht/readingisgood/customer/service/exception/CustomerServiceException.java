package com.ht.readingisgood.customer.service.exception;

public class CustomerServiceException extends RuntimeException {
    private static final long serialVersionUID = -6863738101780947473L;
    public CustomerServiceException(String message) {
        super(message);
    }
}