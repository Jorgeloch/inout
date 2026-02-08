package com.inout.application.usecase.user;

import com.inout.application.dto.income.CreateIncomeDTO;
import com.inout.domain.model.entities.Income;
import com.inout.domain.model.entities.RecurrenceRule;
import com.inout.domain.repository.income.IncomeRepository;
import com.inout.domain.repository.recurrence_rule.RecurrenceRuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AddUserIncomeUseCase {
    private final IncomeRepository incomeRepository;
    private final RecurrenceRuleRepository recurrenceRuleRepository;

    public AddUserIncomeUseCase(
            IncomeRepository incomeRepository,
            RecurrenceRuleRepository recurrenceRuleRepository
    ) {
        this.incomeRepository = incomeRepository;
        this.recurrenceRuleRepository = recurrenceRuleRepository;
    }

    @Transactional
    public Income execute(UUID userId, CreateIncomeDTO dto) {
        UUID incomeId = UUID.randomUUID();

        Income income = new Income(
               userId,
               dto.description(),
               dto.amount(),
               dto.baseDueDate()
        );

        this.incomeRepository.create(income);

        if (income.getRecurrenceRule() != null) {
            RecurrenceRule recurrenceRule = new RecurrenceRule(
                    incomeId,
                    dto.recurrence().frequency(),
                    dto.recurrence().interval(),
                    dto.recurrence().start()
            );

            income.setRecurrenceRule(recurrenceRule);

            this.recurrenceRuleRepository.create(recurrenceRule);
        }

        return income;
    }
}
