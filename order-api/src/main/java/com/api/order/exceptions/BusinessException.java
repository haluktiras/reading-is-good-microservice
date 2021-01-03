package com.api.order.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }
}

