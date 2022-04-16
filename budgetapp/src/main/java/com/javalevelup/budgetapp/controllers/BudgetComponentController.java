package com.javalevelup.budgetApp.controllers;

import com.javalevelup.budgetApp.models.BudgetComponent;
import com.javalevelup.budgetApp.services.BudgetComponentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping(path = "api/v1/budget_components") @RequiredArgsConstructor
public class BudgetComponentController {

    private final BudgetComponentService budgetComponentService;

    @GetMapping(path = "get_budget_component/{id}")
    public ResponseEntity<BudgetComponent> getBudgetComponent(@PathVariable("id") Long id) {
        return ResponseEntity.ok(budgetComponentService.getBudgetComponent(id));
    }

    @PostMapping(name = "save_budget_component")
    public ResponseEntity<BudgetComponent> addNewBudgetComponent(@RequestBody BudgetComponent budgetComponent) {
        return ResponseEntity.ok(budgetComponentService.addNewBudgetComponent(budgetComponent));
    }

    @DeleteMapping(path = "delete_budget_component/{id}")
    public void deleteBudgetComponent(@PathVariable("id") Long id) {
        budgetComponentService.deleteBudgetComponent(id);
    }

    @PutMapping(path = "update_budget_component/{id}")
    public void updateBudgetComponent(@PathVariable("id") Long id,
                                      @RequestBody(required = false) BudgetComponent budgetComponent) {
        budgetComponentService.updateBudgetComponent(id, budgetComponent);
    }
}
