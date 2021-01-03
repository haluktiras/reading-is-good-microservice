package com.api.stock.data;

import com.api.stock.exceptions.BusinessException;
import com.api.stock.models.StockDto;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class StockRepositoryImpl implements StockRepository {

    @Autowired
    private MongoClient client;

    private MongoCollection<StockDto> stockCollection;

    @PostConstruct
    void init() {
        stockCollection = client.getDatabase("test").getCollection("stock", StockDto.class);
    }

    @Override
    public StockDto findOne(String id) {
        return stockCollection.find(eq("stockId", id)).first();
    }

    @Override
    public void update(StockDto stock) {
        StockDto updatingRecord = stockCollection.find(eq("stockId", stock.getStockId())).first();
        if (updatingRecord.getQuantity() <= 0 || updatingRecord.getQuantity() - stock.getQuantity() < 0) {
            throw new BusinessException("No Stock Found", new Exception());
        }
        updatingRecord.setQuantity(updatingRecord.getQuantity() - stock.getQuantity());
        stockCollection.updateOne(eq("stockId", stock.getStockId()), new Document("$set", updatingRecord));
    }

    @Override
    public List<StockDto> findAll() {
        return stockCollection.find().into(new ArrayList<>());
    }

}
