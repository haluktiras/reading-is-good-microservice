package com.api.stock.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(String errorMessage) {
        super(errorMessage);
    }

    public BusinessException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }
}
