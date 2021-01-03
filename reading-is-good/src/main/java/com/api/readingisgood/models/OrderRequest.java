package com.api.readingisgood.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class OrderRequest implements Serializable {
    String orderId;
    String stockId;
    String customerId;
    String status;
    Double quantity;
    Date createdAt;
}
