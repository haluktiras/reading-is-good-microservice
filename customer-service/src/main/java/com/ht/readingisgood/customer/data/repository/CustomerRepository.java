package com.ht.readingisgood.customer.data.repository;

import com.ht.readingisgood.customer.data.model.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {

    boolean existsByEmail(String email);
}