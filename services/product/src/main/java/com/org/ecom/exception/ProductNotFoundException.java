package com.org.ecom.exception;

import lombok.*;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private String message;

    public ProductNotFoundException(String message) {
        super(message);
    }
}
