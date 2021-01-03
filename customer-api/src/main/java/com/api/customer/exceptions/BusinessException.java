package com.api.customer.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }
}

