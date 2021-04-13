package com.ht.readingisgood.customer.service;


import com.ht.readingisgood.customer.service.model.CustomerDto;

public interface CustomerService {

    CustomerDto newCustomer(CustomerDto customerDto);

    CustomerDto getCustomerDetails(String customerId);

    boolean checkExistenceById(String customerId);
}