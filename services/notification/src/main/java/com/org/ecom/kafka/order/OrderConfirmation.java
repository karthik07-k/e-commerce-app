package com.org.ecom.kafka.order;

import com.org.ecom.model.Customer;
import com.org.ecom.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(

        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
