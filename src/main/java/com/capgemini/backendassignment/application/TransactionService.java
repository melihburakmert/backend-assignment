package com.capgemini.backendassignment.application;

import com.capgemini.backendassignment.infrastructure.entity.Customer;
import com.capgemini.backendassignment.infrastructure.entity.Transaction;
import com.capgemini.backendassignment.infrastructure.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public void saveTransaction(final Customer customer, final Long initialCredit) {
        final Transaction transaction = Transaction
                .builder()
                .customer(customer)
                .amount(initialCredit)
                .build();

        transactionRepository.save(transaction);
    }
}
