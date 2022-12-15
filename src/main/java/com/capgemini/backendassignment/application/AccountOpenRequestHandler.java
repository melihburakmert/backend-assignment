package com.capgemini.backendassignment.application;

import com.capgemini.backendassignment.infrastructure.dto.AccountOpenRequestDto;
import com.capgemini.backendassignment.infrastructure.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountOpenRequestHandler {

    private final AccountService accountService;
    private final TransactionService transactionService;
    private final CustomerService customerService;

    public void openAccount(final AccountOpenRequestDto accountOpenRequestDto) {
        final Customer customer = customerService.getCustomer(accountOpenRequestDto.getCustomerId());
        final Long initialCredit = accountOpenRequestDto.getInitialCredit();

        accountService.saveAccount(customer, initialCredit);

        if(initialCredit > 0) {
            transactionService.saveTransaction(customer, initialCredit);
        }
    }
}
