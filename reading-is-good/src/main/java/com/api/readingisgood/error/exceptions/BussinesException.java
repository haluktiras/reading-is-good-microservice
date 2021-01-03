package com.api.readingisgood.error.exceptions;

public class BussinesException extends RuntimeException {

    public BussinesException(String errorMessage) {
        super(errorMessage);
    }

    public BussinesException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }
}
