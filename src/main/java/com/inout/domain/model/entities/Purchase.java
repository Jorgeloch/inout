package com.inout.domain.model.entities;

import com.inout.domain.exception.installment.InvalidNumberOfInstallmentsExceptions;
import com.inout.domain.model.enums.PaymentMethod;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Purchase {
    UUID id;
    UUID userId;
    String description;
    BigDecimal amount;
    PaymentMethod paymentMethod;
    LocalDate purchaseDate;
    List<Installment> installmentList;

    public Purchase(
            UUID id,
            UUID userId,
            String description,
            BigDecimal amount,
            PaymentMethod paymentMethod,
            int numberOfInstallments,
            LocalDate purchaseDate
    ) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.purchaseDate = purchaseDate;

        if (paymentMethod == PaymentMethod.CREDIT) {
            if (numberOfInstallments < 1) {
                throw new InvalidNumberOfInstallmentsExceptions(
                        "there should be at least 1 installment in credit card purchases"
                );
            }

            BigDecimal amountPerInstallment = this.amount
                    .divide(new BigDecimal(numberOfInstallments));
            this.installmentList = new ArrayList<Installment>(numberOfInstallments);
            for (int i = 0; i < numberOfInstallments; i++) {
                this.installmentList.add(
                        new Installment(
                                i + 1,
                                this.id,
                                amountPerInstallment,
                                purchaseDate.plusMonths(i)
                        )
                );
            }
        }
    }
}
