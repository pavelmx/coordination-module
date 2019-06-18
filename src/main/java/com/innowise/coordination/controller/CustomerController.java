package com.innowise.coordination.controller;

import com.innowise.coordination.entity.Customer;
import com.innowise.coordination.service.CustomerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController extends AbstractController<Customer, CustomerService>{

    protected CustomerController(CustomerService service) {
        super(service);
    }
}
