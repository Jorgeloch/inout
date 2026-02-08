package com.inout.application.usecase.user;

import com.inout.domain.model.entities.Income;
import com.inout.domain.repository.income.IncomeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class GetUserIncomesUseCase {
    private final IncomeRepository incomeRepository;

    public GetUserIncomesUseCase(
            IncomeRepository incomeRepository
    ) {
        this.incomeRepository = incomeRepository;
    }

    @Transactional(readOnly = true)
    public List<Income> execute(UUID userId) {
        return this.incomeRepository.findAllActiveByUserId(userId);
    }
}
