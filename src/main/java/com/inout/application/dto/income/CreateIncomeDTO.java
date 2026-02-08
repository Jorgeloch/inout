package com.inout.application.dto.income;

import com.inout.domain.model.entities.RecurrenceRule;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateIncomeDTO(
        @NotNull(message = "description must not be null")
        @NotBlank(message = "description must not be blank")
        @Size(min = 5, max = 100, message = "description must be between 5 to 100 characters long")
        String description,

        @NotNull(message = "amount must be provided")
        @PositiveOrZero(message = "amount must not be negative")
        BigDecimal amount,

        @NotNull(message = "base due date must be provided")
        LocalDate baseDueDate,

        RecurrenceRule recurrence
) {
}
