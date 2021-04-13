package com.ht.readingisgood.securitylibrary.controller.exception.handler;

import com.ht.readingisgood.securitylibrary.controller.exception.SecurityServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SecurityServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {SecurityServiceException.class})
    protected ResponseEntity<Object> handle(RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}