package com.javalevelup.budgetapp.Budget;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/budget")
@AllArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;

    @GetMapping
    public List<Budget> getUserBudgets(){
        return budgetService.getAllBudgets();
    }
}
