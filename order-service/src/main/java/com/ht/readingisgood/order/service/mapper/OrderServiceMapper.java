package com.ht.readingisgood.order.service.mapper;

import com.ht.readingisgood.order.data.model.OrderEntity;
import com.ht.readingisgood.order.service.model.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderServiceMapper {

    OrderServiceMapper INSTANCE = Mappers.getMapper(OrderServiceMapper.class);

    OrderEntity toOrderEntity(OrderDto orderDto);
    OrderDto toOrderDto(OrderEntity orderEntity);
    List<OrderDto> toOrderDtoList(List<OrderEntity> orderEntity);
}