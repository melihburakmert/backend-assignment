package com.capgemini.backendassignment.application;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.capgemini.backendassignment.infrastructure.entity.Customer;
import com.capgemini.backendassignment.infrastructure.entity.Transaction;
import com.capgemini.backendassignment.infrastructure.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import java.util.List;

class TransactionServiceTest {

    public static final long INIT_CREDIT = 500L;

    private final TransactionRepository transactionRepository = mock(TransactionRepository.class);

    private final TransactionService transactionService = new TransactionService(transactionRepository);

    @Test
    void when_customer_and_initial_credit_is_given_then_expect_transaction_is_saved() {

        final Customer customer = Customer
                .builder()
                .customerId(1L)
                .name("name")
                .surname("surname")
                .accounts(List.of())
                .transactions(List.of())
                .build();

        final Transaction transaction = Transaction
                .builder()
                .customer(customer)
                .amount(INIT_CREDIT)
                .build();

        transactionService.saveTransaction(customer, INIT_CREDIT);

        verify(transactionRepository, times(1)).save(transaction);
    }
}