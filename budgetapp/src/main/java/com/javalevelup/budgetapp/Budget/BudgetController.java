package com.javalevelup.budgetapp.Budget;

import com.javalevelup.budgetapp.CashFlow.CashFlow;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/budget")
@AllArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;

    @GetMapping(path = "/{customerId}")
    public List<Budget> getCustomerBudgets(@PathVariable("customerId") Long customerId){
        return budgetService.getCustomerBudgets(customerId);
    }


    @PostMapping
    public void addBudget(@RequestBody Budget budget) {
        budgetService.addBudget(budget);
    }

    @PostMapping(path = "/{id}/add-cash-flow")
    public void addCashflowToBudget(@PathVariable("id") Long id, @RequestBody CashFlow cashFlow){
        budgetService.addCashflowToBudget(id, cashFlow);
    }

    @DeleteMapping (path = "/{id}/remove-cash-flow")
    public void removeCashFlowFromBudget(@PathVariable("id") Long id, @RequestBody CashFlow cashFlow){
        budgetService.removeCashFlowFromBudget(id, cashFlow);
    }

    @PostMapping(value = "/{budgetID}")
    @ResponseStatus(HttpStatus.CREATED)
    public void replicateBudget(@PathVariable("budgetID") Long budgetID){
        budgetService.replicateBudget(budgetID);
    }

    @PatchMapping(value = "/{budgetID}")
    @ResponseStatus(HttpStatus.OK)
    public void modifyBudget(@PathVariable("budgetID")Long budgetID, @RequestParam("budgetName") String budgetName, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate  ){
        budgetService.modifyBudget(budgetID, budgetName, startDate, endDate);
    }

}