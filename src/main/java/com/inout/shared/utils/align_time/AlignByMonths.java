package com.inout.shared.utils.align_time;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

public class AlignByMonths implements AlingStrategy{
    @Override
    public LocalDate align(LocalDate start, LocalDate target, int interval) {
        YearMonth startMonth = YearMonth.from(start);
        YearMonth targetMonth = YearMonth.from(start);

        long diff = ChronoUnit.MONTHS.between(startMonth, targetMonth);
        long steps = (long) Math.ceil((double) diff / interval);

        return start.plusMonths(steps);
    }
}
