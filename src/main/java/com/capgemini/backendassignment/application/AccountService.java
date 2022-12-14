package com.capgemini.backendassignment.application;

import com.capgemini.backendassignment.infrastructure.dto.AccountOpenRequestDto;
import com.capgemini.backendassignment.infrastructure.entity.Account;
import com.capgemini.backendassignment.infrastructure.entity.Customer;
import com.capgemini.backendassignment.infrastructure.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;
    private final CustomerService customerService;

    public Account openAccount(final AccountOpenRequestDto accountOpenRequestDto) {
        final Customer customer = customerService.getCustomer(accountOpenRequestDto.getCustomerId());
        final Long initialCredit = accountOpenRequestDto.getInitialCredit();
        final Account account = Account.builder()
                .customer(customer)
                .balance(initialCredit)
                .build();
        final Account savedAccount = accountRepository.save(account);

        if(initialCredit > 0) {
            transactionService.saveTransaction(customer, initialCredit);
        }
        return savedAccount;
    }
}
