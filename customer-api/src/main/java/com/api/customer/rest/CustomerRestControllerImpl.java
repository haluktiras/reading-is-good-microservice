package com.api.customer.rest;

import com.api.customer.data.CustomerRepository;
import com.api.customer.exceptions.BusinessException;
import com.api.customer.models.CustomerDto;
import com.api.customer.models.CustomerRequest;
import com.api.customer.models.CustomerResponse;
import com.api.customer.models.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerRestControllerImpl implements CustomerRestController {

    private CustomerMapper customerMapper;

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerRestControllerImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public ResponseEntity<CustomerResponse> getCustomer(String customerId) {
        CustomerDto customerDto = customerRepository.findOne(customerId);
        return ResponseEntity.ok(customerMapper.toResponse(customerDto));
    }

    @Override
    public ResponseEntity<CustomerResponse> register(CustomerRequest customerRequest) {
        if(customerRequest.getCustomerId() == null || customerRequest.getPassword() == null) {
            throw new BusinessException("customerId should be fulfilled", new Exception());
        }
        CustomerDto customerDto = customerRepository.findOne(customerRequest.getCustomerId());
        if(customerDto != null){
            throw new BusinessException("customer already exist", new Exception());
        }
        customerRepository.register(customerMapper.toCustomerDto(customerRequest));
        return ResponseEntity.ok().build();
    }

}
