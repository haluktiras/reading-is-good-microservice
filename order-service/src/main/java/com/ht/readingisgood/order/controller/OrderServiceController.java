package com.ht.readingisgood.order.controller;

import com.ht.readingisgood.order.controller.mapper.OrderControllerMapper;
import com.ht.readingisgood.order.controller.model.OrderRequest;
import com.ht.readingisgood.order.controller.model.OrderResponse;
import com.ht.readingisgood.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ht.readingisgood.order.controller.constant.OrderControllerConstants.*;

@RestController
@RequestMapping(value = ORDER_CONTROLLER + V1)
public class OrderServiceController {

    private static final OrderControllerMapper mapper = OrderControllerMapper.INSTANCE;
    private OrderService orderService;

    @Autowired
    public OrderServiceController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = NEW)
    public ResponseEntity<OrderResponse> newOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = mapper.toOrderResponse(
                orderService.placeNewOrder(mapper.toOrderDto(orderRequest)));
        orderResponse.setResponseMessage("New order created.");
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping(value = ALL)
    public ResponseEntity<List<OrderResponse>> getAllOrdersByCustomerId(@RequestParam String customerId) {
        List<OrderResponse> allOrders = mapper
                .toOrderResponseList(orderService.getAllOrdersByCustomerId(customerId));
        allOrders.parallelStream().forEach(o -> o.setResponseMessage("Fetched order details."));
        return ResponseEntity.ok(allOrders);
    }

    @GetMapping(value = ORDER)
    public ResponseEntity<OrderResponse> getOrderDetailsByOrderId(@RequestParam String orderId) {
        OrderResponse orderResponse = mapper.toOrderResponse(
                orderService.getOrderDetailsByOrderId(orderId));
        orderResponse.setResponseMessage("Fetched order detail.");
        return ResponseEntity.ok(orderResponse);
    }
}