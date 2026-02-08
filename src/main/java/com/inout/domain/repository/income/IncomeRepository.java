package com.inout.domain.repository.income;

import com.inout.domain.model.entities.Income;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IncomeRepository {
    void create(Income income);
    void update(Income income);
    void delete(UUID incomeId);
    Optional<Income> findById(UUID incomeId);
    List<Income> findAllActiveByUserId(UUID userId);

}
