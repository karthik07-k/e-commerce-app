package com.org.ecom.model;

public record Customer(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
