package com.innowise.coordination.repository;

import com.innowise.coordination.entity.Customer;
import com.innowise.coordination.entity.QCustomer;
import org.springframework.beans.factory.annotation.Autowired;


public class CustomerIntegrationRepositoryTest extends AbstractIntegrationRepositoryTest<Customer, CustomerRepository, Long, QCustomer> {

    @Autowired
    private CustomerRepository repository;

    private Customer customer;

    @Override
    public Customer getEntity() {
        customer = new Customer(1L, "name","contact","code");
        return customer;
    }



}
