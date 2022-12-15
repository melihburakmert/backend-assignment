package com.capgemini.backendassignment.application;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.capgemini.backendassignment.infrastructure.entity.Account;
import com.capgemini.backendassignment.infrastructure.entity.Customer;
import com.capgemini.backendassignment.infrastructure.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import java.util.List;

class AccountServiceTest {

    public static final long INITIAL_CREDIT = 1000L;
    private final AccountRepository accountRepository = mock(AccountRepository.class);
    private final AccountService accountService = new AccountService(accountRepository);

    @Test
    void when_customer_and_initial_credit_is_given_then_save_account() {

        final Customer customer = Customer
                .builder()
                .customerId(1L)
                .name("name")
                .surname("surname")
                .accounts(List.of())
                .transactions(List.of())
                .build();

        final Account account = Account.builder()
                .customer(customer)
                .balance(INITIAL_CREDIT)
                .build();

        accountService.saveAccount(customer, INITIAL_CREDIT);

        verify(accountRepository, times(1)).save(account);
    }
}