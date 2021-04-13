package com.ht.readingisgood.order.controller.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrderRequest implements Serializable {
    private String orderId;
    private String customerId;
    private String stockId;
    private int quantity;
}