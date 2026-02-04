package transactionproj.com.wealth.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// Records provide a concise syntax to define immutable data carriers.
public record Transaction(
    String id,
    String category,
    BigDecimal amount,
    LocalDateTime timestamp,
    Type type
) {
    public enum Type {
        INCOME, EXPENSE
    }
}