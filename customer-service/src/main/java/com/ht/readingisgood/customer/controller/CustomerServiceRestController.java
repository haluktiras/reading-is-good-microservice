package com.ht.readingisgood.customer.controller;

import com.ht.readingisgood.customer.controller.mapper.CustomerControllerMapper;
import com.ht.readingisgood.customer.controller.model.CustomerRequest;
import com.ht.readingisgood.customer.controller.model.CustomerResponse;
import com.ht.readingisgood.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ht.readingisgood.customer.controller.constant.CustomerControllerConstants.*;

@RestController
@RequestMapping(value = CUSTOMER_CONTROLLER + V1)
public class CustomerServiceRestController {

    private static final CustomerControllerMapper mapper = CustomerControllerMapper.INSTANCE;
    private CustomerService customerService;

    @Autowired
    public CustomerServiceRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = CUSTOMER + NEW)
    public ResponseEntity<CustomerResponse> newCustomer(
            @RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = mapper.toCustomerResponse(customerService
                .newCustomer(mapper.toCustomerDto(customerRequest)));
        customerResponse.setResponseMessage("Customer Created.");
        return ResponseEntity.ok(customerResponse);
    }

    @GetMapping(value = CUSTOMER + DETAIL)
    public ResponseEntity<CustomerResponse> getCustomerDetail(
            @RequestParam String customerId) {
        CustomerResponse customerResponse =
                mapper.toCustomerResponse(
                        customerService.getCustomerDetails(customerId));
        customerResponse.setResponseMessage("Customer Fetched.");
        return ResponseEntity.ok(customerResponse);
    }

    @GetMapping(value = CUSTOMER + EXISTENCE)
    public ResponseEntity<Boolean> isCustomer(
            @RequestParam String customerId) {
        return ResponseEntity.ok(customerService.checkExistenceById(customerId));
    }
}