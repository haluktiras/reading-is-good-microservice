package com.ht.readingisgood.order.controller.mapper;

import com.ht.readingisgood.order.controller.model.OrderRequest;
import com.ht.readingisgood.order.controller.model.OrderResponse;
import com.ht.readingisgood.order.service.model.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderControllerMapper {

    OrderControllerMapper INSTANCE = Mappers.getMapper(OrderControllerMapper.class);

    @Mapping(source = "orderId", target = "orderId")
    OrderResponse toOrderResponse(OrderDto orderDto);

    List<OrderResponse> toOrderResponseList(List<OrderDto> orderDto);

    OrderDto toOrderDto(OrderRequest orderRequest);
}