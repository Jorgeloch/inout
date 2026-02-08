package com.inout.presentation.rest.controller;

import com.inout.application.dto.income.CreateIncomeDTO;
import com.inout.application.dto.purchase.CreatePurchaseDTO;
import com.inout.application.dto.user.responses.GetUserResponseDTO;
import com.inout.application.usecase.user.*;
import com.inout.domain.model.entities.Income;
import com.inout.domain.model.entities.Purchase;
import com.inout.domain.model.entities.User;
import com.inout.domain.repository.purchase.PurchaseFilters;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/users")
public class UserController {
    private final GetUserUseCase getUserUseCase;
    private final GetUserPurchasesUseCase getUserPurchasesUseCase;
    private final GetUserIncomesUseCase getUserIncomesUseCase;
    private final AddUserPurchaseUseCase addUserPurchaseUseCase;
    private final AddUserIncomeUseCase addUserIncomeUseCase;

    public UserController(
            GetUserUseCase getUserUseCase,
            GetUserPurchasesUseCase getUserPurchasesUseCase,
            GetUserIncomesUseCase getUserIncomesUseCase,
            AddUserPurchaseUseCase addUserPurchaseUseCase,
            AddUserIncomeUseCase addUserIncomeUseCase
    ) {
        this.getUserUseCase = getUserUseCase;
        this.getUserPurchasesUseCase = getUserPurchasesUseCase;
        this.getUserIncomesUseCase = getUserIncomesUseCase;
        this.addUserPurchaseUseCase = addUserPurchaseUseCase;
        this.addUserIncomeUseCase = addUserIncomeUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponseDTO> UserInfo(
            @PathVariable UUID id
    ) {
        User user = this.getUserUseCase.execute(id);
        return ResponseEntity.ok(
                new GetUserResponseDTO(
                        user.getId(),
                        user.getName()
                )
        );
    }

    @GetMapping("/{id}/purchases")
    public ResponseEntity<List<Purchase>> getUserPurchases(
            @PathVariable UUID id,
            @RequestBody PurchaseFilters filters
    ) {
        List<Purchase> userPurchases = this.getUserPurchasesUseCase.execute(id, filters);
        return ResponseEntity.ok(userPurchases);
    }

    @PostMapping("/{id}/purchases")
    public ResponseEntity<Purchase> addUserPurchase(
            @PathVariable UUID id,
            @RequestBody @Valid CreatePurchaseDTO dto
    ) {
        Purchase newPurchase = this.addUserPurchaseUseCase.execute(id, dto);
        URI uri = UriComponentsBuilder
                .fromPath("/v1/purchases/{purchaseId}")
                .buildAndExpand(newPurchase.getId())
                .toUri();
        return ResponseEntity.created(uri).body(newPurchase);
    }

    @GetMapping("/{id}/incomes")
    public ResponseEntity<List<Income>> getUserIncomes(
            @PathVariable UUID id
    ) {
        List<Income> userIncomes = this.getUserIncomesUseCase.execute(id);
        return ResponseEntity.ok(userIncomes);
    }

    @PostMapping("/{id}/incomes")
    public ResponseEntity<Income> addUserIncome(
            @PathVariable UUID id,
            @RequestBody @Valid CreateIncomeDTO dto
    ) {
        Income newIncome = this.addUserIncomeUseCase.execute(id, dto);
        URI uri = UriComponentsBuilder
                .fromPath("/v1/income/{purchaseId}")
                .buildAndExpand(newIncome.getId())
                .toUri();
        return ResponseEntity.created(uri).body(newIncome);
    }
}
