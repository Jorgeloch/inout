package com.inout.application.dto.recurrence_rule;

import com.inout.domain.model.enums.RecurrenceFrequency;

import java.time.LocalDate;

public record CreateRecurrenceDTO(
        RecurrenceFrequency frequency,
        int interval,
        LocalDate start
) {
}
