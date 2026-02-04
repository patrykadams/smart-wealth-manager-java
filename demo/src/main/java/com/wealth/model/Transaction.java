package com.wealth.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record Transaction(
    String id,
    String category,
    BigDecimal amount,
    LocalDateTime timestamp,
    Type type
) {
    // Embedded Enum for strict type safety
    public enum Type {
        INCOME, EXPENSE
    }
}