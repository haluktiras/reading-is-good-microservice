package com.ht.readingisgood.stock.service;

import com.ht.readingisgood.stock.data.model.BookStockEntity;
import com.ht.readingisgood.stock.data.repository.BookStockRepository;
import com.ht.readingisgood.stock.service.exceptions.StockServiceException;
import com.ht.readingisgood.stock.service.mapper.StockMapper;
import com.ht.readingisgood.stock.service.models.StockDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockServiceImpl implements StockService {

    private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);
    private static final StockMapper mapper = StockMapper.INSTANCE;
    private BookStockRepository bookStockRepository;

    @Autowired
    public StockServiceImpl(BookStockRepository bookStockRepository) {
        this.bookStockRepository = bookStockRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public StockDto placeOrder(String stockId, int requestedQuantity) {
        try{
            BookStockEntity selectedBook = bookStockRepository
                    .findById(stockId)
                    .orElseThrow(() -> new StockServiceException("Stock Not Found"));
            logger.info("Book Stock Fetched");
            checkStockAvailability(selectedBook.getQuantity(), requestedQuantity);
            logger.info("Book Stock Found");
            updateStocks(selectedBook, requestedQuantity);
            return mapper.toStockDto(selectedBook);
        } catch (Exception e) {
            throw new StockServiceException("Something went wrong while updating stock: " + e.getMessage());
        }
    }

    void checkStockAvailability(int quantityLeft, int quantity) {
        if ((quantityLeft < quantity)) {
            logger.error("There is not enough product.");
            throw new StockServiceException("There is not enough product.");
        }
    }

    public void updateStocks(BookStockEntity selectedBookStock, int requestedQuantity) {

        int quantityAfterPlaceOrder = selectedBookStock.getQuantity() - requestedQuantity;
        selectedBookStock.setQuantity(quantityAfterPlaceOrder);

        try {
            bookStockRepository.save(selectedBookStock);
        } catch (Exception e) {
            throw new StockServiceException("Error while updating stock: " + e.getMessage());
        }
        logger.info("Stock updated successfully.");
    }
}
