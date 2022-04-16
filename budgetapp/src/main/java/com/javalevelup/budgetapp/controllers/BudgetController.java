package com.javalevelup.budgetApp.controllers;

import com.javalevelup.budgetApp.models.Budget;
import com.javalevelup.budgetApp.services.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping(path = "api/v1/budgets") @RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @GetMapping(path = "get_budget/{id}")
    public ResponseEntity<Budget> getBudget(@PathVariable("id") Long id) {
        return ResponseEntity.ok(budgetService.getBudget(id));
    }

    @PostMapping(name = "add_budget")
    public ResponseEntity<Budget> addNewBudget(@RequestBody Budget budget) {
        return ResponseEntity.ok(budgetService.addNewBudget(budget));
    }

    @DeleteMapping(path = "delete_budget/{id}")
    public void deleteBudget(@PathVariable("id") Long id) {
        budgetService.deleteBudget(id);
    }

    @PutMapping(path = "update_budget/{id}")
    public void updateBudget(@PathVariable("id") Long id,
                             @RequestBody(required = false) Budget budget) {
        budgetService.updateBudget(id, budget);
    }
}
