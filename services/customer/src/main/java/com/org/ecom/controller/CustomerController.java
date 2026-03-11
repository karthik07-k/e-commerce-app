package com.org.ecom.controller;

import com.org.ecom.customer.Customer;
import com.org.ecom.model.CustomerRequest;
import com.org.ecom.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid CustomerRequest customer) {
        return ResponseEntity.ok(service.createCustomer(customer));
    }
}
