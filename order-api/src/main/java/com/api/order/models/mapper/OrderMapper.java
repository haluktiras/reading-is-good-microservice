package com.api.order.models.mapper;

import com.api.order.models.OrderDto;
import com.api.order.models.OrderRequest;
import com.api.order.models.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto toOrderDto(OrderRequest orderRequest);

    OrderResponse toResponse(OrderDto orderDto);
}

