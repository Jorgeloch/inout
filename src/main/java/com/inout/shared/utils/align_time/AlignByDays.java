package com.inout.shared.utils.align_time;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AlignByDays implements AlingStrategy {
    @Override
    public LocalDate align(LocalDate start, LocalDate target, int interval) {
        long diff = ChronoUnit.DAYS.between(start, target);
        long steps = (long) Math.ceil((double)diff / interval);
        return start.plusDays(steps);
    }
}
