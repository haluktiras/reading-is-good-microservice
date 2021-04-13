package com.ht.readingisgood.order.controller.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BaseResponse implements Serializable {
    private String responseMessage;
}
