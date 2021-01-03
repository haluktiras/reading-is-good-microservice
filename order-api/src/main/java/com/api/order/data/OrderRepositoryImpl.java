package com.api.order.data;

import com.api.order.exceptions.BusinessException;
import com.api.order.models.OrderDto;
import com.api.order.models.OrderRequest;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private MongoClient client;

    private MongoCollection<OrderDto> orderCollection;

    @PostConstruct
    void init() {
        orderCollection = client.getDatabase("test").getCollection("order", OrderDto.class);
    }

    @Override
    public OrderDto findOne(String id) {
        return orderCollection.find(eq("orderId", id)).first();
    }

    @Override
    public List<OrderDto> findUsersOrders(String customerId) {
        List<OrderDto> usersOrders = orderCollection.find(eq("customerId", customerId)).into(new ArrayList<>());
        return usersOrders;
    }

    @Override
    public void save(OrderDto orderDto) {
        orderDto.setCreatedAt(new Date());
        orderCollection.insertOne(orderDto);
    }

}
