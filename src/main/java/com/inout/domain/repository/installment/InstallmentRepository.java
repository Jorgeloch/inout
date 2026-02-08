package com.inout.domain.repository.installment;

import com.inout.domain.model.entities.Installment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InstallmentRepository {
    void createMany(UUID purchaseId, List<Installment> installments);
    List<Installment> findByPurchaseId(UUID purchaseId);
    Optional<Installment> findById(UUID installmentId);
}
