package com.org.ecom.model;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest (
        @NotNull(message = "Product Id is required")
        Integer productId,
        @NotNull(message = "Quantity is required")
        double quantity

){
}
