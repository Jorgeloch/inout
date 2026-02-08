package com.inout.application.usecase.user;

import com.inout.application.dto.income.CreateIncomeDTO;
import com.inout.domain.model.entities.Income;
import com.inout.domain.repository.income.IncomeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AddUserIncomeUseCase {
    private final IncomeRepository incomeRepository;

    public AddUserIncomeUseCase(
            IncomeRepository incomeRepository
    ) {
        this.incomeRepository = incomeRepository;
    }

    @Transactional
    public Income execute(UUID userId, CreateIncomeDTO dto) {
       Income income = new Income(
               userId,
               dto.description(),
               dto.amount(),
               dto.baseDueDate(),
               dto.recurrence()
       );

       this.incomeRepository.create(income);

       return income;
    }
}
