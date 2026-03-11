package com.org.ecom.service;

import com.org.ecom.customer.Customer;
import com.org.ecom.model.CustomerRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toCreateCustomer(@Valid CustomerRequest customer) {
        if (customer == null) {
            return null;
        }
        return Customer.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .build();
    }
}
