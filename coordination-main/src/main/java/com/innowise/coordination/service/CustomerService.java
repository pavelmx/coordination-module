package com.innowise.coordination.service;

import com.innowise.coordination.entity.Customer;
import com.innowise.coordination.entity.QCustomer;
import com.innowise.coordination.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends AbstractService<Customer, CustomerRepository, QCustomer>{

    public CustomerService(CustomerRepository repository) {
        super(repository);
    }

}