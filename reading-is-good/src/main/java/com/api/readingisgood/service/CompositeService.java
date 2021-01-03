package com.api.readingisgood.service;

import com.api.readingisgood.models.*;

import java.util.List;

public interface CompositeService {

    CustomerResponse findCustomer(String id);

    void register(CustomerRequest customerDto);

    StockResponse getStock(String id);

    List<StockResponse> getStockList();

    void updateStock(StockRequest stockRequest);

    OrderResponse findOne(String id);

    List<OrderResponse> findUsersOrders(String userId);

    void placeOrder(OrderRequest orderRequest);
}
