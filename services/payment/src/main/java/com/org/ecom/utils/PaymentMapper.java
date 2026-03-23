package com.org.ecom.utils;

import com.org.ecom.model.Payment;
import com.org.ecom.model.PaymentRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {


    public Payment toPayment(@Valid PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .orderId(request.orderId())
                .build();
    }
}
