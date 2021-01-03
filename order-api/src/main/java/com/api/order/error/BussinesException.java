package com.api.order.error;

public class BussinesException extends RuntimeException {
    public BussinesException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }
}
