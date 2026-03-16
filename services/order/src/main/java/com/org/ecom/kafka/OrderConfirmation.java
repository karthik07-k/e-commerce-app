package com.org.ecom.kafka;

import com.org.ecom.model.CustomerResponse;
import com.org.ecom.order.PaymentMethod;
import com.org.ecom.remote.model.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customerResponse,
        List<PurchaseResponse> purchaseResponses
) {
}
