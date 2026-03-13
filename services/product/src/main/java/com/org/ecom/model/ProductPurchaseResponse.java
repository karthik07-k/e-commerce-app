package com.org.ecom.model;

import java.math.BigDecimal;

public record ProductPurchaseResponse (
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantityPurchased
){
}
