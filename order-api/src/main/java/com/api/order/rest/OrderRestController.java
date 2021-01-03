package com.api.order.rest;

import com.api.order.models.OrderDto;
import com.api.order.models.OrderRequest;
import com.api.order.models.OrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/orders")
public interface OrderRestController {

    @GetMapping("/order/{id}")
    ResponseEntity<OrderResponse> getOrder(@PathVariable("id") String id);

    @GetMapping("/customersorders/{userId}")
    ResponseEntity<List<OrderResponse>> getUsersOrders(@PathVariable("userId") String userId);

    @PostMapping("/placeorder")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest);
}
