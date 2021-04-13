package com.ht.readingisgood.customer.service;

import com.ht.readingisgood.customer.data.model.CustomerEntity;
import com.ht.readingisgood.customer.data.repository.CustomerRepository;
import com.ht.readingisgood.customer.service.exception.CustomerServiceException;
import com.ht.readingisgood.customer.service.mapper.CustomerServiceMapper;
import com.ht.readingisgood.customer.service.model.CustomerDto;
import org.jeasy.random.EasyRandom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    private EasyRandom generator = new EasyRandom();
    private CustomerDto customerDto;
    private CustomerEntity expectedCustomerEntity;
    private static final CustomerServiceMapper mapper = CustomerServiceMapper.INSTANCE;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        customerDto = generator.nextObject(CustomerDto.class);
        expectedCustomerEntity = mapper.toCustomerEntity(customerDto);
    }

    @Test
    public void testNewCustomer() {
        Mockito.when(customerRepository.save(Mockito.any()))
                .thenReturn(expectedCustomerEntity);
        CustomerDto actualDto =
                customerService.newCustomer(this.customerDto);
        assertThat(mapper.toCustomerDto(expectedCustomerEntity),
                samePropertyValuesAs(actualDto));
    }

    @Test(expected = CustomerServiceException.class)
    public void testNewCustomerException() {
        Mockito.when(customerRepository.save(Mockito.any()))
                .thenThrow(new RuntimeException());
        customerService.newCustomer(this.customerDto);
    }

    @Test
    public void testGetCustomerDetails() {
        Mockito.when(customerRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.of(expectedCustomerEntity));
        CustomerDto actual = customerService
                .getCustomerDetails("customerId");
        assertThat(mapper.toCustomerDto(expectedCustomerEntity),
                samePropertyValuesAs(actual));
    }

    @Test(expected = CustomerServiceException.class)
    public void testGetCustomerDetailsException() {
        Mockito.when(customerRepository.findById(Mockito.anyString()))
                .thenReturn(null);
        customerService.getCustomerDetails("customerId");
    }

    @Test
    public void testCheckExistenceById() {
        Mockito.when(customerRepository
                .existsById(Mockito.anyString()))
                .thenReturn(true);
        boolean actual = customerService
                .checkExistenceById("customerId");
        Assert.assertTrue(actual);
    }

    @Test(expected = CustomerServiceException.class)
    public void testCheckExistenceByIdException() {
        Mockito.when(customerRepository
                .existsById(Mockito.anyString()))
                .thenThrow(new RuntimeException());
        customerService.checkExistenceById("customerId");
    }
}