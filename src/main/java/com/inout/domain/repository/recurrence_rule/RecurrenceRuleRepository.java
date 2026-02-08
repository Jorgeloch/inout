package com.inout.domain.repository.recurrence_rule;

import com.inout.domain.model.entities.RecurrenceRule;

import java.util.Optional;
import java.util.UUID;

public interface RecurrenceRuleRepository {
    void create(RecurrenceRule rule);
    Optional<RecurrenceRule> findByIncomeId(UUID incomeId);
}
