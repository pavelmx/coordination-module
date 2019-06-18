package com.innowise.coordination.repository;

import com.innowise.coordination.entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends AbstractRepository<Customer, Long> {
}
