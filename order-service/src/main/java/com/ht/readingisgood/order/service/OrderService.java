package com.ht.readingisgood.order.service;

import com.ht.readingisgood.order.service.model.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto placeNewOrder(OrderDto orderDto);

    List<OrderDto> getAllOrdersByCustomerId(String customerId);

    OrderDto getOrderDetailsByOrderId(String orderId);
}
