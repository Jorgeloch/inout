package com.inout.application.usecase.user;

import com.inout.domain.model.entities.Purchase;
import com.inout.domain.repository.purchase.PurchaseFilters;
import com.inout.domain.repository.purchase.PurchaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class GetUserPurchasesUseCase {
    private final PurchaseRepository purchaseRepository;

    public GetUserPurchasesUseCase(
            PurchaseRepository purchaseRepository
    ) {
        this.purchaseRepository = purchaseRepository;
    }

    @Transactional(readOnly = true)
    public List<Purchase> execute(
            UUID userId,
            PurchaseFilters filters
    ) {
        return this.purchaseRepository.findByUserId(userId, filters);
    }
}
