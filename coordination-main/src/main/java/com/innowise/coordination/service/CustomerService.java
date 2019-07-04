package com.innowise.coordination.service;

import com.innowise.coordination.entity.Customer;
import com.innowise.coordination.entity.QCustomer;
import com.innowise.coordination.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CustomerService extends AbstractService<Customer, CustomerRepository, QCustomer>{

    @Autowired
    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        super(repository);
    }

    @Override
    public Optional<Customer> save(Customer entity) {
        if(repository.existsByCode(entity.getCode())){
            throw new EntityExistsException("Customer with this code already exists");
        }
        return super.save(entity);
    }

    @Override
    public Optional<Customer> update(Customer entity) {
        if(repository.existsByCode(entity.getCode())){
            throw new EntityNotFoundException("Customer with this code already exists");
        }
        if(!repository.existsById(entity.getId())){
            throw new EntityNotFoundException("Customer with this id not found");
        }
        return super.update(entity);
    }

    @Override
    public void deleteById(Long id) {
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Customer with this id not found");
        }
        super.deleteById(id);
    }
}
