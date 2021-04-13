package com.ht.readingisgood.stock.service.mapper;


import com.ht.readingisgood.stock.data.model.BookStockEntity;
import com.ht.readingisgood.stock.service.models.StockDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StockMapper {

    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    StockDto toStockDto(BookStockEntity bookStockEntity);
}
