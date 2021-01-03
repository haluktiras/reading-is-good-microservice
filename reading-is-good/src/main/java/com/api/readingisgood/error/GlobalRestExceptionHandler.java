package com.api.readingisgood.error;

import com.api.readingisgood.error.exceptions.BussinesException;
import com.api.readingisgood.error.model.RestError;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Order
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalRestExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(ex);
        RestError restError = getRestError(101, "http message is not readable!");
        return new ResponseEntity<>(restError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BussinesException.class})
    public ResponseEntity<RestError> handleBusinessException(BussinesException e, HttpServletRequest req){
        logger.error(e);
        RestError restError = getRestError(2023, e.getMessage());
        return new ResponseEntity<>(restError, HttpStatus.BAD_REQUEST);
    }

    private RestError getRestError(int reasonCode, String messageText){
        return new RestError(reasonCode, messageText);
    }
}
