package com.ht.readingisgood.order.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.ht.readingisgood.order",
        "com.ht.readingisgood.stock",
        "com.ht.readingisgood.securitylibrary"})
@EnableMongoRepositories({ "com.ht.readingisgood.order.data.repository",
        "com.ht.readingisgood.stock.data.repository",
        "com.ht.readingisgood.securitylibrary.data.repository"})
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
