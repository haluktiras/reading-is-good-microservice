package com.api.readingisgood.service;

import com.api.readingisgood.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class CompositeServiceImpl implements CompositeService {

    private RestTemplate restTemplate;

    @Autowired
    public CompositeServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    @Override
    public CustomerResponse findCustomer(String id) {
        return restTemplate.getForObject("http://localhost:8088/customer/" + id, CustomerResponse.class);
    }

    @Override
    public void register(CustomerRequest customerRequest) {
        restTemplate.postForEntity("http://localhost:8088/customer/register", customerRequest, void.class);
    }

    @Override
    public StockResponse getStock(String id) {
        return restTemplate.getForObject("http://localhost:8089/stocks/getStock/" + id, StockResponse.class);
    }

    @Override
    public ArrayList getStockList() {
        return restTemplate.getForObject("http://localhost:8089/stocks/stockList", ArrayList.class);
    }

    @Override
    public void updateStock(StockRequest stockRequest) {
        restTemplate.postForEntity("http://localhost:8089/stocks/updateStock", stockRequest, void.class);
    }

    @Override
    public OrderResponse findOne(String id) {
        return restTemplate.getForObject("http://localhost:8087/orders/order/" + id, OrderResponse.class);
    }

    @Override
    public ArrayList findUsersOrders(String userId) {
        return restTemplate.getForObject("http://localhost:8087/orders/customersorders/" + userId, ArrayList.class);
    }

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        restTemplate.postForEntity("http://localhost:8087/orders/placeorder", orderRequest, void.class);
    }
}
