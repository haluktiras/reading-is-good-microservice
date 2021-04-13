package com.ht.readingisgood.order.service;

import com.ht.readingisgood.order.data.model.OrderEntity;
import com.ht.readingisgood.order.data.repository.OrderRepository;
import com.ht.readingisgood.order.service.exception.OrderServiceException;
import com.ht.readingisgood.order.service.mapper.OrderServiceMapper;
import com.ht.readingisgood.order.service.model.OrderDto;
import com.ht.readingisgood.stock.service.StockService;
import com.ht.readingisgood.stock.service.models.StockDto;
import org.jeasy.random.EasyRandom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerServiceCaller customerServiceCaller;

    @Mock
    private StockService stockService;

    @InjectMocks
    private OrderServiceImpl orderService;

    private static final OrderServiceMapper mapper = OrderServiceMapper.INSTANCE;
    private EasyRandom generator = new EasyRandom();
    private OrderDto orderDto;
    private StockDto bookStockDto;
    private OrderEntity orderEntity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        orderDto = generator.nextObject(OrderDto.class);
        bookStockDto = generator.nextObject(StockDto.class);
        orderEntity = mapper.toOrderEntity(orderDto);
    }

    @Test
    public void testNewOrder() {
        Mockito.when(customerServiceCaller
                .checkCustomerExistence(Mockito.anyString()))
                .thenReturn(true);
        Mockito.when(stockService.placeOrder(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(bookStockDto);
        Mockito.when(orderRepository.save(Mockito.any()))
                .thenReturn(mapper.toOrderEntity(orderDto));
        OrderDto response = orderService.placeNewOrder(orderDto);
        Assert.assertNotNull(response);
    }

    @Test(expected = OrderServiceException.class)
    public void testNewOrderException() {
        Mockito.when(customerServiceCaller
                .checkCustomerExistence(Mockito.anyString()))
                .thenReturn(false);
        orderService.placeNewOrder(orderDto);
    }

    @Test
    public void testGetAllOrdersByCustomerId() {
        Mockito.when(orderRepository.findByCustomerId(Mockito.anyString()))
                .thenReturn(Collections.singletonList(orderEntity));
        List<OrderDto> responseList = orderService
                .getAllOrdersByCustomerId("customerId");
        assertThat(orderDto, samePropertyValuesAs(responseList.get(0)));
    }

    @Test(expected = OrderServiceException.class)
    public void testGetAllOrdersByCustomerIdException() {
        Mockito.when(orderRepository.findByCustomerId(Mockito.anyString()))
                .thenReturn(Collections.emptyList());
        orderService.getAllOrdersByCustomerId("customerId");
    }

    @Test
    public void testGetOrderDetailsByOrderId() {
        Mockito.when(orderRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.of(orderEntity));
        OrderDto response = orderService
                .getOrderDetailsByOrderId("orderId");
        assertThat(orderDto, samePropertyValuesAs(response));
    }

    @Test(expected = OrderServiceException.class)
    public void testGetOrderDetailsByOrderIdException() {
        Mockito.when(orderRepository.findById(Mockito.anyString()))
                .thenReturn(null);
        orderService.getOrderDetailsByOrderId("orderId");
        ;
    }
}