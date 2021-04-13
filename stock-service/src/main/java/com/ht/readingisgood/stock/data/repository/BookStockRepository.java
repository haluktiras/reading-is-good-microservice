package com.ht.readingisgood.stock.data.repository;

import com.ht.readingisgood.stock.data.model.BookStockEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookStockRepository extends MongoRepository<BookStockEntity, String> {
}
