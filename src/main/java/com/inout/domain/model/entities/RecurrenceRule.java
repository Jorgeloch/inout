package com.inout.domain.model.entities;

import com.inout.application.mapper.FrequencyMapper;
import com.inout.domain.model.enums.RecurrenceFrequency;
import com.inout.shared.utils.align_time.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@AllArgsConstructor
public class RecurrenceRule {
    UUID incomeId;
    @Setter
    RecurrenceFrequency frequency;
    @Setter
    int interval;
    LocalDate startDate;
    boolean active = true;

    public RecurrenceRule(
        UUID incomeId,
        RecurrenceFrequency frequency,
        int interval,
        LocalDate startDate
    ) {
        this.incomeId = incomeId;
        this.frequency = frequency;
        this.interval = interval;
        this.startDate = startDate;
    }

    private LocalDate alignCursor(LocalDate target, AlingStrategy strategy) {
        return strategy.align(startDate, target, interval);
    }

    private LocalDate next(LocalDate cursor) {
        return switch (frequency) {
            case DAILY -> cursor.plusDays(interval);
            case WEEKLY -> cursor.plusWeeks(interval);
            case MONTHLY -> cursor.plusMonths(interval);
            case YEARLY -> cursor.plusYears(interval);
        };
    }

    public List<LocalDate> generateMonthlyOccurrences(YearMonth target) {
        if (!isActive()) return List.of();

        LocalDate monthStart = target.atDay(1);
        LocalDate monthEnd = target.atEndOfMonth();
        LocalDate cursor = alignCursor(
                monthStart,
                FrequencyMapper.frequencyToStrategy(frequency)
        );

        List<LocalDate> occurrences = new ArrayList<LocalDate>();
        while (cursor.isBefore(monthEnd)) {
            occurrences.add(cursor);
            cursor = next(cursor);
        }
        return occurrences;
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }
}
