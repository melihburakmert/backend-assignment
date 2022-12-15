package com.capgemini.backendassignment.application;

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

    public void saveAccount(final Customer customer, final Long initialCredit) {
        final Account account = Account.builder()
                .customer(customer)
                .balance(initialCredit)
                .build();
        accountRepository.save(account);
    }
}
