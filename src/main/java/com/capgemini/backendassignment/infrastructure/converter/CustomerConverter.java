package com.capgemini.backendassignment.infrastructure.converter;

import com.capgemini.backendassignment.infrastructure.dto.CustomerDto;
import com.capgemini.backendassignment.infrastructure.entity.Account;
import com.capgemini.backendassignment.infrastructure.entity.Customer;
import com.capgemini.backendassignment.infrastructure.entity.Transaction;

public final class CustomerConverter {

    private CustomerConverter() {
        throw new IllegalStateException("Utility class.");
    }

    public static CustomerDto convertToDto(final Customer customer) {
        return CustomerDto
                .builder()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .transactions(
                        customer
                                .getTransactions()
                                .stream()
                                .map(Transaction::getTransactionId)
                                .toList())
                .accounts(
                        customer
                                .getAccounts()
                                .stream()
                                .map(Account::getAccountId)
                                .toList())
                .balance(customer
                        .getAccounts()
                        .stream()
                        .map(Account::getBalance)
                        .reduce(0L, Long::sum))
                .build();
    }
}
