package com.capgemini.backendassignment.infrastructure.repository;

import com.capgemini.backendassignment.infrastructure.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
