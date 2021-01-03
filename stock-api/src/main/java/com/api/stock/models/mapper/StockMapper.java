package com.api.stock.models.mapper;

import com.api.stock.models.StockDto;
import com.api.stock.models.StockRequest;
import com.api.stock.models.StockResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {

    StockDto toStockDto(StockRequest stockRequest);

    StockResponse toResponse(StockDto stockDto);
}
