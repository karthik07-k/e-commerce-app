package com.org.ecom.service;

import com.org.ecom.customer.Customer;
import com.org.ecom.model.CustomerRequest;
import com.org.ecom.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;
    @Autowired
    private CustomerMapper mapper;

    public Customer createCustomer(@Valid CustomerRequest customer) {
        return repository.save(mapper.toCreateCustomer(customer));
    }
}
