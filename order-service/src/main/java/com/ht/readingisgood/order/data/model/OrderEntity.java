package com.ht.readingisgood.order.data.model;

import com.ht.readingisgood.order.service.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "orders")
@Getter
@Setter
public class OrderEntity {

    private @Id
    String orderId;
    private String customerId;
    private String stockId;
    private int quantity;
    private Date orderPlacedOn;
    private Date deliveryTargetDate;
    private OrderStatus orderStatus;
    private Double totalPrice;
}
