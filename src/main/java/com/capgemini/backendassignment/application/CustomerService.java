package com.capgemini.backendassignment.application;

import com.capgemini.backendassignment.infrastructure.converter.CustomerConverter;
import com.capgemini.backendassignment.infrastructure.dto.CustomerDto;
import com.capgemini.backendassignment.infrastructure.entity.Customer;
import com.capgemini.backendassignment.infrastructure.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer getCustomer(final Long customerId) {
        return customerRepository
                .findById(customerId)
                .orElseThrow();
    }

    public CustomerDto getCustomerDetails(final Long customerId) {
        return CustomerConverter.convertToDto(getCustomer(customerId));
    }
}
