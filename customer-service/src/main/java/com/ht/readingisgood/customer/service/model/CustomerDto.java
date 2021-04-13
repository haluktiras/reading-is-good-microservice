package com.ht.readingisgood.customer.service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {

    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
}