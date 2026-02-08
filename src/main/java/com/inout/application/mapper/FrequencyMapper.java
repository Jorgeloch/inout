package com.inout.application.mapper;

import com.inout.domain.model.enums.RecurrenceFrequency;
import com.inout.shared.utils.align_time.*;

import java.util.Map;

public class FrequencyMapper {
    private static final Map<RecurrenceFrequency, AlingStrategy> mapper = Map.of(
            RecurrenceFrequency.DAILY, new AlignByDays(),
            RecurrenceFrequency.WEEKLY, new AlignByWeeks(),
            RecurrenceFrequency.MONTHLY, new AlignByMonths(),
            RecurrenceFrequency.YEARLY, new AlignByYears()
    );

    public static AlingStrategy frequencyToStrategy(RecurrenceFrequency frequency) {
        return mapper.get(frequency);
    }
}
