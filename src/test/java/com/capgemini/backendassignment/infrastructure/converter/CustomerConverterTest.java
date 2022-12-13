package com.capgemini.backendassignment.infrastructure.converter;

import static org.junit.jupiter.api.Assertions.*;

import com.capgemini.backendassignment.infrastructure.dto.CustomerDto;
import com.capgemini.backendassignment.infrastructure.entity.Account;
import com.capgemini.backendassignment.infrastructure.entity.Customer;
import com.capgemini.backendassignment.infrastructure.entity.Transaction;
import org.junit.jupiter.api.Test;
import java.util.List;

class CustomerConverterTest {

    public static final long CUSTOMER_ID = 1L;
    public static final long BALANCE_1 = 100L;
    public static final long BALANCE_2 = 300L;

    @Test
    void when_customer_entity_is_given_expect_matching_dto() {

        final Customer customer = Customer
                .builder()
                .customerId(CUSTOMER_ID)
                .name("name")
                .surname("surname")
                .accounts(List.of())
                .transactions(List.of())
                .build();

        final Account account1 = Account
                .builder()
                .accountId(1L)
                .balance(BALANCE_1)
                .customer(customer)
                .build();

        final Account account2 = Account
                .builder()
                .accountId(2L)
                .balance(BALANCE_2)
                .customer(customer)
                .build();

        final Transaction transaction1 = Transaction
                .builder()
                .customer(customer)
                .transactionId(1L)
                .amount(BALANCE_1)
                .build();

        final Transaction transaction2 = Transaction
                .builder()
                .customer(customer)
                .transactionId(2L)
                .amount(BALANCE_1)
                .build();

        customer.setAccounts(List.of(account1, account2));
        customer.setTransactions(List.of(transaction1, transaction2));

        final CustomerDto customerDto = CustomerConverter.convertToDto(customer);

        assertEquals(customerDto.getCustomerId(), customer.getCustomerId());
        assertEquals(customerDto.getName(), customer.getName());
        assertEquals(customerDto.getSurname(), customer.getSurname());
        assertEquals(customerDto.getBalance(), BALANCE_1+BALANCE_2);
    }
}