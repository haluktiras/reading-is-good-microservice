package com.ht.readingisgood.order.controller.model;

import com.ht.readingisgood.order.service.model.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class OrderResponse extends BaseResponse {

    private String orderId;
    private String customerId;
    private String stockId;
    private int quantity;
    private Date orderPlacedOn;
    private Date deliveryTargetDate;
    private OrderStatus orderStatus;
    private Double totalPrice;

}