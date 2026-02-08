package com.inout.application.usecase.user;

import com.inout.application.dto.purchase.CreatePurchaseDTO;
import com.inout.domain.model.entities.Purchase;
import com.inout.domain.model.enums.PaymentMethod;
import com.inout.domain.repository.installment.InstallmentRepository;
import com.inout.domain.repository.purchase.PurchaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AddUserPurchaseUseCase {
    private final PurchaseRepository purchaseRepository;
    private final InstallmentRepository installmentRepository;

    public AddUserPurchaseUseCase(
            PurchaseRepository purchaseRepository,
            InstallmentRepository installmentRepository
    ) {
        this.purchaseRepository = purchaseRepository;
        this.installmentRepository = installmentRepository;
    }

    @Transactional
    public Purchase execute(UUID userId, CreatePurchaseDTO dto) {
        UUID purchaseId = UUID.randomUUID();
        Purchase purchase = new Purchase(
                purchaseId,
                userId,
                dto.description(),
                dto.amount(),
                dto.paymentMethod(),
                dto.numberOfInstallments(),
                dto.purchaseDate()
        );

        if (dto.paymentMethod() == PaymentMethod.CREDIT) {
           this.purchaseRepository.create(purchase);
           this.installmentRepository.createMany(purchaseId, purchase.getInstallmentList());
        }
        else {
            this.purchaseRepository.create(purchase);
        }

        return purchase;
    }
}
