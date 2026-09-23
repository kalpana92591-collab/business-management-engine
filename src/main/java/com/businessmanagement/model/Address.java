package com.businessmanagement.model;

public record Address(
        String street,
        String city,
        String state,
        String postalCode
) {
}