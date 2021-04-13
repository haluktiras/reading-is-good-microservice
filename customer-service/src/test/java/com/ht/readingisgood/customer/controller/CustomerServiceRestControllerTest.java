package com.ht.readingisgood.customer.controller;

import com.ht.readingisgood.customer.controller.mapper.CustomerControllerMapper;
import com.ht.readingisgood.customer.controller.model.CustomerRequest;
import com.ht.readingisgood.customer.controller.model.CustomerResponse;
import com.ht.readingisgood.customer.service.CustomerService;
import com.ht.readingisgood.customer.service.model.CustomerDto;
import org.jeasy.random.EasyRandom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class CustomerServiceRestControllerTest {

    @Mock
    private CustomerService customerService;
    @InjectMocks
    private CustomerServiceRestController restController;

    private static final CustomerControllerMapper mapper = CustomerControllerMapper.INSTANCE;
    private EasyRandom generator = new EasyRandom();
    private CustomerRequest customerRequest;
    private CustomerDto customerDto;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        customerRequest = generator.nextObject(CustomerRequest.class);
        customerDto = mapper.toCustomerDto(customerRequest);
    }

    @Test
    public void testNewCustomer() {
        Mockito.when(customerService.newCustomer(Mockito.any()))
                .thenReturn(customerDto);
        CustomerResponse response = restController
                .newCustomer(customerRequest).getBody();
        Assert.assertEquals("Customer Created.", Optional.ofNullable(
                response).orElseThrow(RuntimeException::new).getResponseMessage());
    }

    @Test
    public void testGetCustomerDetail() {
        Mockito.when(customerService.getCustomerDetails(Mockito.any()))
                .thenReturn(customerDto);
        CustomerResponse response = restController
                .getCustomerDetail("customerId").getBody();
        Assert.assertEquals("Customer Fetched.", Optional.ofNullable(
                response).orElseThrow(RuntimeException::new).getResponseMessage());
    }

    @Test
    public void testIsCustomer() {
        Mockito.when(customerService.checkExistenceById(Mockito.any()))
                .thenReturn(Boolean.TRUE);
        Boolean customerId = restController
                .isCustomer("customerId").getBody();
        Assert.assertTrue(customerId);
    }
}