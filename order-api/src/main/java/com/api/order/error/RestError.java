package com.api.order.error;

import lombok.Value;

import java.io.Serializable;

@Value
public class RestError implements Serializable {

    int returnCode;
    String messageText;
}
