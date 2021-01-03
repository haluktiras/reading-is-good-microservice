package com.api.customer.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Data
@EqualsAndHashCode
@ToString
public class CustomerDto {
    private String customerId;
    private String password;
    private String name;
    private String address;
    private String phone;
    private Date createdAt = new Date();
}


