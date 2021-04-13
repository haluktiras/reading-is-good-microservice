package com.ht.readingisgood.customer.service;

import com.ht.readingisgood.customer.data.model.CustomerEntity;
import com.ht.readingisgood.customer.data.repository.CustomerRepository;
import com.ht.readingisgood.customer.service.exception.CustomerServiceException;
import com.ht.readingisgood.customer.service.mapper.CustomerServiceMapper;
import com.ht.readingisgood.customer.service.model.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger =
            LoggerFactory.getLogger(CustomerServiceImpl.class);
    private static final CustomerServiceMapper mapper = CustomerServiceMapper.INSTANCE;
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto newCustomer(CustomerDto customerDto) {
        return mapper.toCustomerDto(saveCustomer(customerDto));
    }

    @Override
    public CustomerDto getCustomerDetails(String customerId) {
        Optional<CustomerEntity> customerEntity;
        try {
            customerEntity =
                    customerRepository.findById(customerId);
            return mapper.toCustomerDto(
                    customerEntity.orElseThrow(() ->
                            new CustomerServiceException(
                                    "Customer could not found by given Id.")));
        } catch (Exception e) {
            throw new CustomerServiceException(
                    "Error while finding customer: " + e.getMessage());
        }
    }

    @Override
    public boolean checkExistenceById(String customerId) {
        logger.info("Customer is checking...");
        try {
            return customerRepository.existsById(customerId);
        } catch (Exception e) {
            throw new CustomerServiceException(
                    "Error while checking existence: " + e.getMessage());
        }
    }

    boolean checkExistenceByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    CustomerEntity saveCustomer(CustomerDto customerDto) {
        CustomerEntity customerEntity;
        if (!checkExistenceByEmail(customerDto.getEmail())) {
            try {
                customerEntity = customerRepository
                        .save(mapper.toCustomerEntity(customerDto));
                logger.info("Customer saved with the name: {}", customerEntity.getFirstName());
            } catch (Exception e) {
                throw new CustomerServiceException(
                        "Error while checking existence: " + e.getMessage());
            }
            return customerEntity;
        }
        throw new CustomerServiceException("Could not save customer: already exists.");
    }
}