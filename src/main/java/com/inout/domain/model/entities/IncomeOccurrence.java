package com.inout.domain.model.entities;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
public class IncomeOccurrence {
    UUID id;
    UUID incomeId;
    UUID userId;
    LocalDate occurrenceDate;
    BigDecimal amount;

    public IncomeOccurrence(
            UUID incomeId,
            UUID userId,
            LocalDate occurrenceDate,
            BigDecimal amount
    ) {
        this.id = UUID.randomUUID();
        this.incomeId = incomeId;
        this.userId = userId;
        this.occurrenceDate = occurrenceDate;
        this.amount = amount;
    }
}
