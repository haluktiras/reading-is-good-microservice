package com.ht.readingisgood.securitylibrary.controller.exception;

public class SecurityServiceException extends SecurityException {
    private static final long serialVersionUID = -6243494449201174223L;
    public SecurityServiceException(String message, Exception ex) {
        super(message, ex);
    }
    public SecurityServiceException(String message) {
        super(message);
    }
}