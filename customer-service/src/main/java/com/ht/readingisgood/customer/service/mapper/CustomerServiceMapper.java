package com.ht.readingisgood.customer.service.mapper;

import com.ht.readingisgood.customer.data.model.CustomerEntity;
import com.ht.readingisgood.customer.service.model.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerServiceMapper {

    CustomerServiceMapper INSTANCE = Mappers.getMapper(CustomerServiceMapper.class);

    CustomerEntity toCustomerEntity(CustomerDto customerDto);

    CustomerDto toCustomerDto(CustomerEntity customerEntity);
}