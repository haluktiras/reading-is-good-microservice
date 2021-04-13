package com.ht.readingisgood.order.service;

import com.ht.readingisgood.order.service.exception.OrderServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Component
public class CustomerServiceCallerImpl implements CustomerServiceCaller {

    private RestTemplate restTemplate;

    @Value("${customer.service.host:localhost:8080}")
    private String customerServiceHost;
    @Value("${customer.service.path.root:/customer-service/V1}")
    private String customerServiceRootPath;
    @Value("${customer.service.path.checkExistence:/customer/existence}")
    private String existenceServicePath;

    @Autowired
    public CustomerServiceCallerImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean checkCustomerExistence(String customerId) {

        UriComponents customerServiceUri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(customerServiceHost)
                .path(customerServiceRootPath + existenceServicePath)
                .queryParam("customerId", customerId).build();

        ResponseEntity<Boolean> customerResponse;
        try {
            customerResponse = restTemplate.getForEntity(customerServiceUri.toString(), Boolean.class);
        } catch (Exception e) {
            throw new OrderServiceException("Error while calling customer service: {" + e.getMessage() + "}");
        }

        return Optional.ofNullable(customerResponse.getBody())
                .orElseThrow(() -> new OrderServiceException("Could not get response from customer service."));
    }
}
