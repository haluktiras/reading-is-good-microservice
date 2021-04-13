package com.ht.readingisgood.stock.service;

import com.ht.readingisgood.stock.data.model.BookStockEntity;
import com.ht.readingisgood.stock.service.exceptions.StockServiceException;
import com.ht.readingisgood.stock.service.models.StockDto;
import com.ht.readingisgood.stock.data.repository.BookStockRepository;
import com.ht.readingisgood.stock.service.mapper.StockMapper;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.jeasy.random.EasyRandom;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.Optional;

public class StockServiceTest {

    @Mock
    private BookStockRepository bookStockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    private static final StockMapper mapper = StockMapper.INSTANCE;
    private EasyRandom generator = new EasyRandom();
    private BookStockEntity bookStockEntity;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        bookStockEntity = generator.nextObject(BookStockEntity.class);
        bookStockEntity.setQuantity(13);
        Mockito.when(bookStockRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(bookStockEntity));
        Mockito.when(bookStockRepository.save(ArgumentMatchers.any()))
                .thenReturn(bookStockEntity);
    }

    @Test
    public void testPlaceOrder(){
        StockDto stockDto = stockService.placeOrder("stockId", 2);
        MatcherAssert.assertThat(mapper.toStockDto(bookStockEntity), Matchers.samePropertyValuesAs(stockDto));
    }

    @Test(expected = StockServiceException.class)
    public void testPlaceOrderException(){
        stockService.placeOrder("stockId", 15);
    }

}
