package com.api.readingisgood.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class CustomerRequest implements Serializable {
    String customerId;
    String password;
    String name;
    String address;
    String phone;
    Date createdAt;
}