package com.api.customer.data;

import com.api.customer.models.CustomerDto;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    private MongoClient client;

    private MongoCollection<CustomerDto> customerCollection;

    @PostConstruct
    void init() {
        customerCollection = client.getDatabase("test").getCollection("customer", CustomerDto.class);
    }

    @Override
    public CustomerDto findOne(String id) {
        return customerCollection.find(eq("customerId", id)).first();
    }

    @Override
    public void register(CustomerDto customerDto) {
        customerCollection.insertOne(customerDto);
    }

}
