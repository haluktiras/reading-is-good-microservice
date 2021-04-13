package com.ht.readingisgood.order.service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {

    private String orderId;
    private String customerId;
    private String stockId;
    private int quantity;
    private Date orderPlacedOn;
    private Date deliveryTargetDate;
    private OrderStatus orderStatus;
    private Double totalPrice;
}
