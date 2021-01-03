package com.api.customer.models.mapper;

import com.api.customer.models.CustomerDto;
import com.api.customer.models.CustomerRequest;
import com.api.customer.models.CustomerResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto toCustomerDto(CustomerRequest customerRequest);

    CustomerResponse toResponse(CustomerDto customerDto);
}

