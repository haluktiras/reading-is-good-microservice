package com.ht.readingisgood.order.controller;

import com.ht.readingisgood.order.controller.mapper.OrderControllerMapper;
import com.ht.readingisgood.order.controller.model.OrderRequest;
import com.ht.readingisgood.order.controller.model.OrderResponse;
import com.ht.readingisgood.order.service.OrderService;
import com.ht.readingisgood.order.service.model.OrderDto;
import org.jeasy.random.EasyRandom;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OrderServiceControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderServiceController orderServiceController;

    private static final OrderControllerMapper mapper = OrderControllerMapper.INSTANCE;
    private EasyRandom generator = new EasyRandom();
    private OrderDto orderDto;
    private OrderRequest orderRequest;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        orderRequest = generator.nextObject(OrderRequest.class);
        orderDto = mapper.toOrderDto(orderRequest);
    }

    @Test
    public void testNewOrder() {
        Mockito.when(orderService.placeNewOrder(Mockito.any()))
                .thenReturn(orderDto);
        OrderResponse response = orderServiceController.newOrder(orderRequest).getBody();
        Assert.assertEquals("New order created.",
                Optional.ofNullable(response)
                        .orElseThrow(RuntimeException::new).getResponseMessage());
    }

    @Test
    public void testGetAllOrdersByCustomerId() {
        Mockito.when(orderService.getAllOrdersByCustomerId(Mockito.anyString()))
                .thenReturn(Collections.singletonList(orderDto));
        List<OrderResponse> response = orderServiceController
                .getAllOrdersByCustomerId("customerId").getBody();
        Assert.assertEquals("Fetched order details.",
                Optional.ofNullable(response)
                        .orElseThrow(RuntimeException::new).get(0)
                        .getResponseMessage());
    }

    @Test
    public void testGetOrderDetailsByOrderId() {
        Mockito.when(orderService.getOrderDetailsByOrderId(
                Mockito.anyString())).thenReturn(orderDto);
        OrderResponse response = orderServiceController
                .getOrderDetailsByOrderId("orderId").getBody();
        Assert.assertEquals("Fetched order detail.",
                Optional.ofNullable(response)
                        .orElseThrow(RuntimeException::new)
                        .getResponseMessage());
    }

}
