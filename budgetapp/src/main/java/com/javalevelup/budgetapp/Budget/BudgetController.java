package com.javalevelup.budgetapp.Budget;

import com.javalevelup.budgetapp.CashFlow.CashFlow;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/budget")
@AllArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;

    @GetMapping(path = "{username}/get-budgets")
    public List<Budget> getCustomerBudgets(@PathVariable("username") String username, Principal principal) {
        if (username.equals(principal.getName())) {
            return budgetService.getCustomerBudgets(username);
        }
        throw new IllegalStateException(
                "User not authorized to access information");
    }


    @PostMapping(path = "{username}/add-budget")
    public void addBudget(@PathVariable("username") String username, @RequestBody Budget budget, Principal principal) {
        if (username.equals(principal.getName())) {
            budgetService.addBudget(username, budget);
        } else throw new IllegalStateException(
                "User not authorized to access information");
    }

    @PostMapping(path = "/{id}/add-cash-flow")
    public void addCashflowToBudget(@PathVariable("id") Long id, @RequestBody CashFlow cashFlow){
        budgetService.addCashflowToBudget(id, cashFlow);
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

    @PutMapping(value = "/{budgetID}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBudget(@PathVariable("budgetID")Long budgetID, @RequestBody Budget budget){
        budgetService.updateBudget(budgetID, budget);
    }

}