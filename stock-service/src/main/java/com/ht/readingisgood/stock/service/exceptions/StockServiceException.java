package com.ht.readingisgood.stock.service.exceptions;

public class StockServiceException extends RuntimeException {

    public StockServiceException(String errorMessage) {
        super(errorMessage);
    }
}
