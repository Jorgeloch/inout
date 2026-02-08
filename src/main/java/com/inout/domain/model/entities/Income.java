package com.inout.domain.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Income {
    private final UUID Id;
    private final UUID userId;

    @Setter
    private String description;
    @Setter
    private BigDecimal amount;
    @Setter
    private LocalDate baseDueDate;

    private RecurrenceRule recurrenceRule;
    private boolean active = true;

    public Income(
            UUID userId,
            String description,
            BigDecimal amount,
            LocalDate baseDueDate,
            RecurrenceRule recurrenceRule
    ) {
        this.Id = UUID.randomUUID();
        this.userId = userId;
        this.description = description;
        this.amount = amount;
        this.baseDueDate = baseDueDate;
        this.recurrenceRule = recurrenceRule;
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }
}
