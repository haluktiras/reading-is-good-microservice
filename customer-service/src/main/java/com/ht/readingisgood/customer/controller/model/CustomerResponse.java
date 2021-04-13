package com.ht.readingisgood.customer.controller.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse extends BaseResponse {

    private static final long serialVersionUID = -2794751464603984357L;
    private String customerId;
    private String firstName;
}