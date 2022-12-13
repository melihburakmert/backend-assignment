package com.capgemini.backendassignment.infrastructure.repository;

import com.capgemini.backendassignment.infrastructure.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
