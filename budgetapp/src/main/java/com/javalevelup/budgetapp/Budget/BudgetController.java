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
    @ResponseStatus(HttpStatus.OK)
    public List<Budget> getCustomerBudgets(@PathVariable("username") String customerId){
        return budgetService.getCustomerBudgets(customerId);
    }


    @PostMapping(path = "{username}/add-budget")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBudget(@PathVariable("username") String username, @RequestBody Budget budget) {
        budgetService.addBudget(username, budget);
    }

    @PostMapping(path = "/{budgetID}/add-cash-flow")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCashflowToBudget(@PathVariable("budgetID") Long id, @RequestBody CashFlow cashFlow){
        System.out.println("Have you been hit?");
        budgetService.addCashflowToBudget(id, cashFlow);
    }

    @DeleteMapping (path = "/{budgetID}/remove-cash-flow")
    @ResponseStatus(HttpStatus.OK)
    public void removeCashFlowFromBudget(@PathVariable("budgetID") Long id, @RequestBody CashFlow cashFlow) {
        budgetService.removeCashFlowFromBudget(id, cashFlow);
    }

    @GetMapping(value="/{budgetID}")
    @ResponseStatus(HttpStatus.OK)
    public Budget getSingleBudgetById(@PathVariable("budgetID") Long budgetID){
        return budgetService.getBudgetByID(budgetID);
    }

    @GetMapping(path = "incomes/{budgetID}")
    @ResponseStatus(HttpStatus.OK)
    public List<CashFlow> getBudgetIncomes(@PathVariable("budgetID") Long budgetID){
        return budgetService.getBudgetIncomes(budgetID);
    }

    @GetMapping(path = "expenses/{budgetID}")
    @ResponseStatus(HttpStatus.OK)
    public List<CashFlow> getBudgetExpenses(@PathVariable("budgetID") Long budgetID){
        return budgetService.getBudgetExpenses(budgetID);
    }

    @GetMapping(path = "balance/{budgetID}")
    @ResponseStatus(HttpStatus.OK)
    public Double getBudgetBalance(@PathVariable("budgetID") Long budgetID){
        return budgetService.getBudgetBalance(budgetID);
    }

    @PostMapping(value = "replicate/{budgetID}")
    @ResponseStatus(HttpStatus.CREATED)
    public void replicateBudget(@PathVariable("budgetID") Long budgetID){
        budgetService.replicateBudget(budgetID);
    }

    @PatchMapping(value = "/{budgetID}")
    @ResponseStatus(HttpStatus.OK)
    public void modifyBudget(@PathVariable("budgetID")Long budgetID, @RequestParam("budgetName") String budgetName, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
        budgetService.modifyBudget(budgetID, budgetName, startDate, endDate);
    }

}