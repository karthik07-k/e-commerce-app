package com.org.ecom.model;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Product description is required")
        String description,
        @Positive(message = "Available Quantity is positive")
        double availableQuantity,
        @Positive(message = "Product Price should be is positive")
        BigDecimal price,
        @NotNull(message = "Category Id is required")
        Integer categoryId
) {
}
