package com.api.customer.rest;

import com.api.customer.models.CustomerRequest;
import com.api.customer.models.CustomerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
public interface CustomerRestController {

    @GetMapping("/{id}")
    ResponseEntity<CustomerResponse> getCustomer(@PathVariable("id") String id);

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<CustomerResponse> register(@RequestBody CustomerRequest customerRequest);
}
