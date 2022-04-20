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

    public List<Budget> getBudgets(){
        return budgetService.getUserBudgets(1L);
    }

//    @GetMapping(value="/{budgetID}")
//    public Budget getOneBudget(Long budgetID){
//        return budgetService.getBudget(budgetID);
//    }
//
////    @PostMapping
////    @ResponseStatus(HttpStatus.CREATED)
////    public void addBudget(@RequestBody String name, Date startDate, Date endDate, User user){
////        budgetService.addBudget(name, (java.sql.Date) startDate, (java.sql.Date) endDate, user);
////    }
//
//    @PostMapping(value = "/{budgetID}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void replicateBudget(@RequestBody Long budgetID){
//        budgetService.replicateBudget(budgetID);
//    }
//
//    @PatchMapping
//    @ResponseStatus(HttpStatus.OK)
//    public void modifyBudget(@RequestBody Long budgetID, String budgetName, java.sql.Date startDate, java.sql.Date endDate){
//        budgetService.modifyBudget(budgetID, budgetName, startDate, endDate);
//    }

}