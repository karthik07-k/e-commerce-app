package com.org.ecom.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String message;
}
