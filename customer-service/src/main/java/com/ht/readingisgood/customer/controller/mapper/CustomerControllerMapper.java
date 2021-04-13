package com.ht.readingisgood.customer.controller.mapper;

import com.ht.readingisgood.customer.controller.model.CustomerRequest;
import com.ht.readingisgood.customer.controller.model.CustomerResponse;
import com.ht.readingisgood.customer.service.model.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerControllerMapper {

    CustomerControllerMapper INSTANCE = Mappers.getMapper(CustomerControllerMapper.class);

    @Mapping(source = "customerId", target = "customerId")
    @Mapping(source = "firstName", target = "firstName")
    CustomerResponse toCustomerResponse(CustomerDto customerDto);

    CustomerDto toCustomerDto(CustomerRequest customerRequest);
}