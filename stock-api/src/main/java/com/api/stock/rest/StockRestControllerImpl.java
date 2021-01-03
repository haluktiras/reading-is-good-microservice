package com.api.stock.rest;

import com.api.stock.data.StockRepository;
import com.api.stock.exceptions.BusinessException;
import com.api.stock.models.StockDto;
import com.api.stock.models.StockRequest;
import com.api.stock.models.StockResponse;
import com.api.stock.models.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class StockRestControllerImpl implements StockRestController {

    private StockMapper stockMapper;

    private StockRepository stockRepository;

    @Autowired
    public StockRestControllerImpl(StockRepository stockRepository, StockMapper stockMapper) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }


    @Override
    public ResponseEntity<StockResponse> getStock(String stockId) {
        StockDto orderDto = stockRepository.findOne(stockId);
        return ResponseEntity.ok(stockMapper.toResponse(orderDto));
    }

    @Override
    public ResponseEntity<List<StockResponse>> getStockList() {
        List<StockResponse> stockResponses = stockRepository.findAll().stream()
                .map(stockMapper::toResponse)
                .collect(Collectors.toList());
        return Objects.nonNull(stockResponses) ? ResponseEntity.ok(stockResponses)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public ResponseEntity<StockResponse> updateStock(StockRequest request) {
        stockRepository.update(stockMapper.toStockDto(request));
        return ResponseEntity.ok().build();
    }
}
