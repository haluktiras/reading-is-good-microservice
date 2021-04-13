package com.ht.readingisgood.customer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication( exclude = EmbeddedMongoAutoConfiguration.class,
        scanBasePackages = {"com.ht.readingisgood.customer",
        "com.ht.readingisgood.securitylibrary"})
@EnableMongoRepositories({"com.ht.readingisgood.customer.data.repository",
        "com.ht.readingisgood.securitylibrary.data.repository"})
public class CustomerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}