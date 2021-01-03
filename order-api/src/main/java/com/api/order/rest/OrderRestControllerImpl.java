package com.api.order.rest;

import com.api.order.data.OrderRepository;
import com.api.order.models.OrderDto;
import com.api.order.models.OrderRequest;
import com.api.order.models.OrderResponse;
import com.api.order.models.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderRestControllerImpl implements OrderRestController {

    private OrderMapper orderMapper;

    private OrderRepository orderRepository;

    @Autowired
    public OrderRestControllerImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public ResponseEntity<OrderResponse> getOrder(String orderId) {
        OrderDto orderDto = orderRepository.findOne(orderId);
        return ResponseEntity.ok(orderMapper.toResponse(orderDto));
    }

    @Override
    public ResponseEntity<List<OrderResponse>> getUsersOrders(String userId) {
        List<OrderResponse> orderResponse = orderRepository.findUsersOrders(userId).stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
        if(orderResponse.isEmpty()){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(orderResponse);
    }

    @Override
    public ResponseEntity<OrderResponse> placeOrder(OrderRequest orderRequest) {
        orderRepository.save(orderMapper.toOrderDto(orderRequest));
        return ResponseEntity.ok().build();
    }

}
