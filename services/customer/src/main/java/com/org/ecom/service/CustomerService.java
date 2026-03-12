package com.org.ecom.service;

import com.org.ecom.customer.Customer;
import com.org.ecom.exception.CustomerNotFoundException;
import com.org.ecom.model.CustomerRequest;
import com.org.ecom.model.CustomerResponse;
import com.org.ecom.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;
    @Autowired
    private CustomerMapper mapper;

    public Customer createCustomer(@Valid CustomerRequest customer) {
        return repository.save(mapper.toCreateCustomer(customer));
    }

    public List<CustomerResponse> getAllCustomers() {
        return repository.findAll()
                .stream()
                .map(mapper::buildCustomerResponse)
                .collect(Collectors.toList());
    }

    public Customer updateCustomer(CustomerRequest customer) {
        if (customer.getId() == null) {
            throw new IllegalArgumentException("Customer ID is required for update");
        }
        Customer existingCustomer = repository.findById(customer.getId())
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Customer with ID %s not found", customer.getId())
                ));
        mergeCustomer(existingCustomer, customer);
        return repository.save(existingCustomer);
    }

    private void mergeCustomer(Customer existingCustomer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.getFirstName())) {
            existingCustomer.setFirstName(request.getFirstName());
        }
        if (StringUtils.isNotBlank(request.getLastName())) {
            existingCustomer.setLastName(request.getLastName());
        }
        if (StringUtils.isNotBlank(request.getEmail())) {
            existingCustomer.setEmail(request.getEmail());
        }
        if (request.getAddress() != null) {
            existingCustomer.setAddress(request.getAddress());
        }
    }

    public CustomerResponse getCustomer(String customerId) {
        return repository.existsById(customerId) ?
                mapper.buildCustomerResponse(repository.findById(customerId).orElseThrow(() ->
                        new CustomerNotFoundException(format("There is no customer found with customer Id %s, So unable to find the customer data", customerId)))) :
                null;
    }

    public String deleteCustomer(String customerId) {
        if (!repository.existsById(customerId)) {
            throw new CustomerNotFoundException(format("Customer with ID %s not found", customerId));
        }
        repository.deleteById(customerId);
        return format("Customer with ID %s has been deleted successfully", customerId);
    }
}
