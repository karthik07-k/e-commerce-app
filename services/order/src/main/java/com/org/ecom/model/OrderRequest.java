package com.org.ecom.model;

import com.org.ecom.order.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest (
        Integer id,
        String reference,
        @Positive(message = "Amount must be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method is required")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer ID is required")
        String customerId,
        @NotEmpty(message = "Products list cannot be empty")
        List<PurchaseRequest> products
) {
}
