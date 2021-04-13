package com.ht.readingisgood.order.data.repository;

import com.ht.readingisgood.order.data.model.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, String> {

    List<OrderEntity> findByCustomerId(String customerId);
}
