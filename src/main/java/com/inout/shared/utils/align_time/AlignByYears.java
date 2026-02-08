package com.inout.shared.utils.align_time;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AlignByYears implements AlingStrategy{
    @Override
    public LocalDate align(LocalDate start, LocalDate target, int interval) {
        long diff = ChronoUnit.YEARS.between(start, target);
        long steps = (long) Math.ceil((double) diff / interval);
        return start.plusYears(steps);
    }
}
