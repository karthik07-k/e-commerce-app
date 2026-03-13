package com.org.ecom.exception;

public class ProductPurchaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ProductPurchaseException(String message) {
        super(message);
    }
}
