package com.ht.readingisgood.order.service;

import com.ht.readingisgood.order.service.exception.OrderServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CustomerServiceCallerTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CustomerServiceCallerImpl customerServiceCaller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCheckCustomerExistence() {
        Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any()))
                .thenReturn(ResponseEntity.ok(Boolean.TRUE));
        boolean response = customerServiceCaller
                .checkCustomerExistence("customerId");
        Assert.assertTrue(response);
    }

    @Test(expected = OrderServiceException.class)
    public void testCheckCustomerExistenceException() {
        Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any()))
                .thenThrow(new RuntimeException());
        customerServiceCaller
                .checkCustomerExistence("customerId");
    }
}