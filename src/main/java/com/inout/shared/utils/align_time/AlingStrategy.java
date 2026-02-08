package com.inout.shared.utils.align_time;

import java.time.LocalDate;

public interface AlingStrategy {
    LocalDate align(LocalDate start, LocalDate target, int interval);
}
