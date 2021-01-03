package com.api.customer.data;

import com.api.customer.models.CustomerDto;

import java.util.List;

public interface CustomerRepository {

    CustomerDto findOne(String id);

    void register(CustomerDto customerDto);
}
