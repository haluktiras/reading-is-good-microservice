package com.api.readingisgood.rest;

import com.api.readingisgood.error.exceptions.BussinesException;
import com.api.readingisgood.models.*;
import com.api.readingisgood.service.CompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompositeRestControllerImpl implements CompositeRestController {

    private CompositeService compositeService;

    @Autowired
    public CompositeRestControllerImpl(CompositeService compositeService) {
        this.compositeService = compositeService;
    }

    @Override
    public ResponseEntity<CustomerResponse> getCustomer(String id) {
        CustomerResponse customerResponse = compositeService.findCustomer(id);
        return ResponseEntity.ok(customerResponse);
    }

    @Override
    public ResponseEntity<StockResponse> getStock(String id) {
        StockResponse stockResponse = compositeService.getStock(id);
        if(stockResponse == null) throw new BussinesException("stock not found");
        return ResponseEntity.ok(stockResponse);
    }

    @Override
    public ResponseEntity<List<StockResponse>> getStockList() {
        List<StockResponse> stockResponses = compositeService.getStockList();
        return ResponseEntity.ok(stockResponses);
    }

    @Override
    public ResponseEntity<StockResponse> updateStock(StockRequest request) {
        compositeService.updateStock(request);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<OrderResponse> getOrder(String id) {
        OrderResponse orderResponse = compositeService.findOne(id);
        if(orderResponse == null) throw new BussinesException("no order found");
        return ResponseEntity.ok(orderResponse);
    }

    @Override
    public ResponseEntity<List<OrderResponse>> getUsersOrders(String userId) {
        List<OrderResponse> orderResponseList = compositeService.findUsersOrders(userId);
        if (orderResponseList == null || orderResponseList.isEmpty())
            throw new BussinesException("no order found", new Exception());
        return ResponseEntity.ok(orderResponseList);
    }

    @Override
    public ResponseEntity<OrderRequest> placeOrder(OrderRequest orderRequest) {
        if (compositeService.findCustomer(orderRequest.getCustomerId()) == null)
            throw new BussinesException("no customer found");
        try {
            compositeService.updateStock(StockRequest.builder()
                    .stockId(orderRequest.getStockId())
                    .quantity(orderRequest.getQuantity())
                    .build());
        } catch (BussinesException e) {
            throw new BussinesException("no stock", e);
        }
        compositeService.placeOrder(orderRequest);
        return ResponseEntity.ok(orderRequest);
    }
}
