package com.api.readingisgood.rest;

import com.api.readingisgood.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping("/readingisgood")
public interface CompositeRestController {

    @GetMapping("/customer/{id}")
    ResponseEntity<CustomerResponse> getCustomer(@PathVariable("id") String id);

    @GetMapping("/stock/getstock/{id}")
    ResponseEntity<StockResponse> getStock(@PathVariable("id") String id);

    @GetMapping("/stock/stocklist")
    ResponseEntity<List<StockResponse>> getStockList();

    @PostMapping("/stock/updatestock")
    ResponseEntity<StockResponse> updateStock(@RequestBody StockRequest request);

    @GetMapping("/order/{id}")
    ResponseEntity<OrderResponse> getOrder(@PathVariable("id") String id);

    @GetMapping("/customersorders/{userId}")
    ResponseEntity<List<OrderResponse>> getUsersOrders(@PathVariable("userId") String userId);

    @PostMapping("/placeorder")
    ResponseEntity<OrderRequest> placeOrder(@RequestBody OrderRequest orderRequest) throws Exception;

}
