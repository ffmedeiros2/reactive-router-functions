package com.example.routerfunctions.util;

import com.example.routerfunctions.model.Money;

public class MoneyUtils {
    private MoneyUtils() {
    }

    public static Money convert(final Money from, final Money baseTo) {
        return Money.builder()
                .value(from.getValue().multiply(baseTo.getValue()))
                .currency(baseTo.getCurrency())
                .build();
    }
}
