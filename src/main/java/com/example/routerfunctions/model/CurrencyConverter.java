package com.example.routerfunctions.model;

import java.math.BigDecimal;

import com.example.routerfunctions.enums.Currency;

import lombok.Getter;

@Getter
public class CurrencyConverter {
    Boolean success;
    Query query;
    Info info;
    BigDecimal result;

    @Getter
    public static class Query {
        Currency from;
        Currency to;
        BigDecimal amount;
    }

    @Getter
    public static class Info {
        Long timestamp;
        Long quote;
    }
}
