package com.api.stock.data;

import com.api.stock.models.StockDto;

import java.util.List;

public interface StockRepository {

    StockDto findOne(String id);

    void update(StockDto stock);

    List<StockDto> findAll();
}
