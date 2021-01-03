package com.api.order.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class OrderDto {

    private String orderId;
    private String stockId;
    private String customerId;
    private String status;
    private Date createdAt;
}


