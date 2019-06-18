package com.innowise.coordination.repository.postgres;

import com.innowise.coordination.entity.Customer;
import com.innowise.coordination.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class CustomerRepositoryTests extends AbstractRepositoryTests<Customer, CustomerRepository, Long> {

    @Autowired
    private CustomerRepository repository;

    private Customer customer;

    @Override
    public Customer getEntity() {
        customer = new Customer(1L, "name","contact","code");
        customer = repository.save(customer);
        return customer;
    }

    @Override
    public Long getEntityId() {
        return customer.getId();
    }


}
