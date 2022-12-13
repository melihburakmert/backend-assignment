package com.capgemini.backendassignment.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.capgemini.backendassignment.infrastructure.entity.Customer;
import com.capgemini.backendassignment.infrastructure.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;

class CustomerServiceTest {

    public static final long CUSTOMER_ID = 1L;
    private final CustomerRepository customerRepository = mock(CustomerRepository.class);

    private final CustomerService customerService = new CustomerService(customerRepository);

    @Test
    void when_customer_id_is_given_then_trigger_repository_call() {

        final Customer expectedCustomer = Customer
                .builder()
                .customerId(CUSTOMER_ID)
                .name("name")
                .surname("surname")
                .accounts(List.of())
                .transactions(List.of())
                .build();

        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(expectedCustomer));

        final Customer customer = customerService.getCustomer(CUSTOMER_ID);

        verify(customerRepository, times(1)).findById(CUSTOMER_ID);
        assertEquals(expectedCustomer, customer);
    }
}