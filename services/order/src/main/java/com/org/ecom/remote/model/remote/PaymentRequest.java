package com.org.ecom.remote.model.remote;

import com.org.ecom.model.CustomerResponse;
import com.org.ecom.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
