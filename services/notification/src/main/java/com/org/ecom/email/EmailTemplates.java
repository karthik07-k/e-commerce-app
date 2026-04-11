package com.org.ecom.email;

import lombok.Getter;

public enum EmailTemplates {
    PAYMENT_CONFIRMATION("payment-confirmation.html", "payment successfully completed"),
    ORDER_CONFIRMATION("order-confirmation.html", "your order has been placed successfully"),
    ORDER_DELIVERED("order-delivered-template", "payment successfully completed");

    @Getter
    private final String templateName;
    @Getter
    private final String subject;

    EmailTemplates(String templateName, String subject) {
        this.templateName = templateName;
        this.subject = subject;
    }
}
