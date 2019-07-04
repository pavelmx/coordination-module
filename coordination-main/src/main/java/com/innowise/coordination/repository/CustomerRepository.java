package com.innowise.coordination.repository;

import com.innowise.coordination.entity.Customer;
import com.innowise.coordination.entity.QCustomer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends AbstractRepository<Customer, Long, QCustomer> {

    boolean existsByCode(String code);
}
