package com.ht.readingisgood.stock.service;

import com.ht.readingisgood.stock.service.models.StockDto;

public interface StockService {

    StockDto placeOrder(String stockId, int quantity);
}
