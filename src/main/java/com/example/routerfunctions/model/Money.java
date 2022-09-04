package com.example.routerfunctions.model;

import java.math.BigDecimal;

import com.example.routerfunctions.enums.Currency;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Money {
    private BigDecimal value;
    private Currency currency;
}
