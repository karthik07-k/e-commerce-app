package com.org.ecom.remote;

import com.org.ecom.model.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "${application.config.customer-url")
public interface CustomerClient {
    @GetMapping("/exists/{customer-id}")
    ResponseEntity<CustomerResponse> getCustomer(@PathVariable("customer-id") String customerId);
}
