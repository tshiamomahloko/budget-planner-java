package com.javalevelup.budgetapp.Budget;

import com.javalevelup.budgetapp.CashFlow.CashFlow;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/budget")
@AllArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;

    @GetMapping(path = "/{id}")
    public List<Budget> getBudgets(@PathVariable("id") Long customerId){
        return budgetService.getCustomerBudgets(customerId);
    }

    @PostMapping
    public void addBudget(@RequestBody Budget budget) {
        budgetService.addBudget(budget);
    }

//    @GetMapping(value="/{budgetID}")
//    public Budget getOneBudget(Long budgetID){
//        return budgetService.getBudget(budgetID);
//    }
//
//
    @PostMapping(value = "/{budgetID}")
    @ResponseStatus(HttpStatus.CREATED)
    public void replicateBudget(@PathVariable("budgetID") Long budgetID){
        budgetService.replicateBudget(budgetID);
    }
//
//    @PatchMapping
//    @ResponseStatus(HttpStatus.OK)
//    public void modifyBudget(@RequestBody Long budgetID, String budgetName, java.sql.Date startDate, java.sql.Date endDate){
//        budgetService.modifyBudget(budgetID, budgetName, startDate, endDate);
//    }

}