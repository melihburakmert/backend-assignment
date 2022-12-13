package com.capgemini.backendassignment.infrastructure.repository;

import com.capgemini.backendassignment.infrastructure.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
