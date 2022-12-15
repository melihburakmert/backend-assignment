package com.capgemini.backendassignment.application;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.capgemini.backendassignment.infrastructure.dto.AccountOpenRequestDto;
import com.capgemini.backendassignment.infrastructure.entity.Customer;
import org.junit.jupiter.api.Test;

class AccountOpenRequestHandlerTest {

    public static final long INITIAL_CREDIT = 1000L;
    public static final long ZERO_CREDIT = 0;
    public static final long CUSTOMER_ID = 1L;
    private final AccountService accountService = mock(AccountService.class);
    private final TransactionService transactionService = mock(TransactionService.class);
    private final CustomerService customerService = mock(CustomerService.class);

    private final AccountOpenRequestHandler requestHandler =
            new AccountOpenRequestHandler(accountService, transactionService, customerService);

    @Test
    void when_a_request_given_with_init_credit_and_customer_id_then_handle_request() {

        final Customer customer = mock(Customer.class);
        when(customerService.getCustomer(CUSTOMER_ID)).thenReturn(customer);

        final AccountOpenRequestDto accountOpenRequestDto = anAccountOpenRequestDto(INITIAL_CREDIT);

        requestHandler.openAccount(accountOpenRequestDto);

        verify(customerService, times(1)).getCustomer(CUSTOMER_ID);
        verify(accountService, times(1)).saveAccount(customer, INITIAL_CREDIT);
        verify(transactionService, times(1)).saveTransaction(customer, INITIAL_CREDIT);
    }

    @Test
    void when_a_request_given_with_zero_credit_and_customer_id_then_handle_request() {

        final Customer customer = mock(Customer.class);
        when(customerService.getCustomer(CUSTOMER_ID)).thenReturn(customer);

        final AccountOpenRequestDto accountOpenRequestDto = anAccountOpenRequestDto(ZERO_CREDIT);

        requestHandler.openAccount(accountOpenRequestDto);

        verify(customerService, times(1)).getCustomer(CUSTOMER_ID);
        verify(accountService, times(1)).saveAccount(customer, ZERO_CREDIT);
        verifyNoInteractions(transactionService);
    }

    private AccountOpenRequestDto anAccountOpenRequestDto(final Long initialCredit) {
        return AccountOpenRequestDto
                .builder()
                .initialCredit(initialCredit)
                .customerId(CUSTOMER_ID)
                .build();
    }
}