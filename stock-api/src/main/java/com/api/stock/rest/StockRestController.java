package com.api.stock.rest;

import com.api.stock.models.StockRequest;
import com.api.stock.models.StockResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/stocks")
public interface StockRestController {

    @GetMapping("/getStock/{id}")
    ResponseEntity<StockResponse> getStock(@PathVariable("id") String id);

    @GetMapping("/stockList")
    ResponseEntity<List<StockResponse>> getStockList();

    @PostMapping("/updateStock")
    ResponseEntity<StockResponse> updateStock(@RequestBody StockRequest request);
}
