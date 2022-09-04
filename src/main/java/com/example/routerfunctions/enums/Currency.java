package com.example.routerfunctions.enums;

import java.util.Optional;

public enum Currency {
    BRL,
    EUR,
    USD;

    public static final String DEFAULT_CURRENCY = "USD";

    public static Currency valueOf(final Optional<String> value) {
        String currency = value.orElse(DEFAULT_CURRENCY);
        return Currency.valueOf(currency);
    }
}
