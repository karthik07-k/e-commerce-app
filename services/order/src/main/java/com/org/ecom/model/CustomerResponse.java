package com.org.ecom.model;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
