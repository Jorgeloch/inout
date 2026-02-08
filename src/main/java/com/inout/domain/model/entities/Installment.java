package com.inout.domain.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Installment {
   UUID purchaseId;
   int id;
   BigDecimal amount;
   LocalDate dueDate;

   Installment(
           int id,
           UUID purchaseId,
           BigDecimal amount,
           LocalDate dueDate
   ) {
      this.id = id;
      this.amount = amount;
      this.dueDate = dueDate;
   }
}
