package com.org.ecom.remote;

import com.org.ecom.remote.model.remote.Payment;
import com.org.ecom.remote.model.remote.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", url = "${application.config.payment-url}")
public interface PaymentClient {

    @PostMapping
    Payment requestOrderPayment(@RequestBody PaymentRequest request);
}
