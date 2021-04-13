package com.ht.readingisgood.customer.controller.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CustomerRequest implements Serializable {

    private static final long serialVersionUID = -4498735220490085823L;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
}