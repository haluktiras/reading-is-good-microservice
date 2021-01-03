package com.api.stock.exceptions;

import lombok.Value;

import java.io.Serializable;

@Value
public class RestError implements Serializable {

    int returnCode;
    String messageText;
}
