package com.inout.application.dto.purchase;

import com.inout.domain.model.enums.PaymentMethod;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreatePurchaseDTO(
        @NotNull(message = "description must be provided")
        @NotBlank(message = "description must not be blank")
        @Size(min = 5, max = 100, message = "description must be between 5 to 100 characters")
        String description,

        @NotNull(message = "amount must be provided")
        @Positive(message = "amount must be a positive value")
        BigDecimal amount,

        @NotNull(message = "payment method must be provided")
        PaymentMethod paymentMethod,

        Integer numberOfInstallments,

        @NotNull(message = "purchase date must be provided")
        @NotBlank(message = "purchase date must not be blank")
        LocalDate purchaseDate
) {
}
