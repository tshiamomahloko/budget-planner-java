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

    @GetMapping(path = "{username}/get-budgets")
    public List<Budget> getCustomerBudgets(@PathVariable("username") String customerId){
        return budgetService.getCustomerBudgets(customerId);
    }


    @PostMapping(path = "{username}/add-budget")
    public void addBudget(@PathVariable("username") String username, @RequestBody Budget budget) {
        budgetService.addBudget(username, budget);
    }

    @PostMapping(path = "/{id}/add-cash-flow")
    public void addCashflowToBudget(@PathVariable("id") Long id, @RequestBody CashFlow cashFlow){
        budgetService.addCashflowToBudget(id, cashFlow);
    }

    @DeleteMapping (path = "/{id}/remove-cash-flow")
    public void removeCashFlowFromBudget(@PathVariable("id") Long id, @RequestBody CashFlow cashFlow) {
        budgetService.removeCashFlowFromBudget(id, cashFlow);
    }

    @GetMapping(value="/{budgetID}")
    public Budget getSingleBudgetById(@PathVariable("budgetID") Long budgetID){
        return budgetService.getBudgetByID(budgetID);
    }

    @GetMapping(path = "incomes/{budgetID}")
    public List<CashFlow> getBudgetIncomes(@PathVariable("budgetID") Long budgetID){
        return budgetService.getBudgetIncomes(budgetID);
    }

    @GetMapping(path = "expenses/{budgetID}")
    public List<CashFlow> getBudgetExpenses(@PathVariable("budgetID") Long budgetID){
        return budgetService.getBudgetExpenses(budgetID);
    }

    @GetMapping(path = "balance/{budgetID}")
    public Double getBudgetBalance(@PathVariable("budgetID") Long budgetID){
        return budgetService.getBudgetBalance(budgetID);
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