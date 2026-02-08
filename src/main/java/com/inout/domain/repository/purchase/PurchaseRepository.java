package com.inout.domain.repository.purchase;

import com.inout.domain.model.entities.Purchase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PurchaseRepository {
   void create(Purchase purchase);
   List<Purchase> findByUserId(UUID userId, PurchaseFilters filters);
   Optional<Purchase> findById(UUID purchaseId);
}
