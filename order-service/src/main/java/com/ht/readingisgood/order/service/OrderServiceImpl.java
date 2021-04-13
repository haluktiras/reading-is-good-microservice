package com.ht.readingisgood.order.service;

import com.ht.readingisgood.order.data.model.OrderEntity;
import com.ht.readingisgood.order.data.repository.OrderRepository;
import com.ht.readingisgood.order.service.exception.OrderServiceException;
import com.ht.readingisgood.order.service.mapper.OrderServiceMapper;
import com.ht.readingisgood.order.service.model.OrderDto;
import com.ht.readingisgood.order.service.model.OrderStatus;
import com.ht.readingisgood.stock.service.StockService;
import com.ht.readingisgood.stock.service.models.StockDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private static final OrderServiceMapper mapper = OrderServiceMapper.INSTANCE;

    private OrderRepository orderRepository;
    private CustomerServiceCaller customerServiceCaller;
    private StockService stockService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerServiceCaller customerServiceCaller, StockService stockService) {
        this.orderRepository = orderRepository;
        this.customerServiceCaller = customerServiceCaller;
        this.stockService = stockService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public OrderDto placeNewOrder(OrderDto orderDto) {
        orderDto.setOrderStatus(OrderStatus.RECEIVED);

        synchronized (this) {

            try {
                logger.info("Calling customer service for existence check...");
                checkCustomerExistence(orderDto.getCustomerId());

                StockDto updatedBookStock = updateStockService(orderDto);
                logger.info("Stock service updated.");

                OrderEntity savedEntity = placeOrder(orderDto, updatedBookStock.getPrice());
                logger.info("Order placed by orderService with the id: {}", savedEntity.getOrderId());

                return mapper.toOrderDto(savedEntity);
            } catch (Exception e) {
                throw new OrderServiceException("Could not place order: " + e.getMessage());
            }

        }
    }

    private void checkCustomerExistence(String customerId) {
        if (!customerServiceCaller.checkCustomerExistence(customerId)) {
            throw new OrderServiceException("Customer does not exist.");
        }
    }

    private StockDto updateStockService(OrderDto orderDto) {
        return stockService.placeOrder(orderDto.getStockId(), orderDto.getQuantity());
    }

    private OrderEntity placeOrder(OrderDto orderDto, Double priceForOne) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        orderDto.setOrderPlacedOn(calendar.getTime());
        calendar.add(Calendar.DATE, 5);
        orderDto.setDeliveryTargetDate(calendar.getTime());
        orderDto.setOrderStatus(OrderStatus.ACCEPTED);
        orderDto.setTotalPrice(orderDto.getQuantity() * priceForOne);
        return orderRepository.save(mapper.toOrderEntity(orderDto));
    }

    @Override
    public List<OrderDto> getAllOrdersByCustomerId(String customerId) {
        List<OrderEntity> orderEntityList;
        try {
            orderEntityList = orderRepository.findByCustomerId(customerId);
            orderEntityList.removeAll(Collections.singleton(null));
            if (orderEntityList.isEmpty()) {
                throw new OrderServiceException("No order found by given customerId.");
            }
            return mapper.toOrderDtoList(orderEntityList);
        } catch (Exception e) {
            throw new OrderServiceException("Error while fetching orders: " + e.getMessage());
        }
    }

    @Override
    public OrderDto getOrderDetailsByOrderId(String orderId) {
        Optional<OrderEntity> orderEntity;
        try {
            orderEntity = orderRepository.findById(orderId);
            return mapper.toOrderDto(orderEntity.orElseThrow(
                    () -> new OrderServiceException("Could not find any order by given Id.")));
        } catch (Exception e) {
            throw new OrderServiceException("Error while fetching Order: " + e.getMessage());
        }
    }
}
