package com.ht.readingisgood.customer.controller.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BaseResponse implements Serializable {
    private String responseMessage;
    private static final long serialVersionUID = 191097391410614557L;
}