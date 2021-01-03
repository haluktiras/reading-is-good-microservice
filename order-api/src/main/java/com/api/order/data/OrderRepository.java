package com.api.order.data;

import com.api.order.models.OrderDto;
import com.api.order.models.OrderRequest;

import java.util.List;

public interface OrderRepository {

    OrderDto findOne(String id);

    List<OrderDto> findUsersOrders(String userId);

    void save(OrderDto orderDto);
}
