package com.org.ecom.controller;

import com.org.ecom.customer.Customer;
import com.org.ecom.model.CustomerRequest;
import com.org.ecom.model.CustomerResponse;
import com.org.ecom.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid CustomerRequest customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createCustomer(customer));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(service.getAllCustomers());
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody @Valid CustomerRequest customer) {
        return ResponseEntity.ok(service.updateCustomer(customer));
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(service.getCustomer(customerId));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCustomer(@RequestParam("customer-id") String customerId) {
        String deleteRecord = service.deleteCustomer(customerId);
        if (deleteRecord != null) {
            return ResponseEntity.ok(deleteRecord);
        }
        return ResponseEntity.notFound().build();
    }
}
