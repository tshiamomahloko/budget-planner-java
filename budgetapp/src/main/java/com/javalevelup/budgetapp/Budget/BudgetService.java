package com.javalevelup.budgetapp.Budget;

//import com.javalevelup.budgetapp.CashFlow.CashFlow;
//import com.javalevelup.budgetapp.User.User;
import com.amazonaws.services.identitymanagement.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import static java.lang.Long.valueOf;

@AllArgsConstructor
@Service
public class BudgetService {
    @Autowired
    private final BudgetRepository budgetRepository;
    private List<Budget> userBudgets;

//    public List<Budget> getUserBudgets(){
//        return budgetRepository.findAll();
//    }

    public List<Budget> getUserBudgets(Long userID){
        this.userBudgets = budgetRepository.getCustomerBudgets(userID);
        return userBudgets;
    }


    public void replicateBudget(Long budgetID){
        Budget budget = budgetRepository.findById(budgetID).get();
        budgetRepository.save(new Budget(budget.getName(), budget.getStartDate(), budget.getEndDate(), budget.getCustomer()));

    }

    public void modifyBudget(Long budgetID, String budgetName, Date startDate, Date endDate){
        Budget modifiedBudget = budgetRepository.findById(budgetID).get();
        modifiedBudget.setName(budgetName);
        modifiedBudget.setStartDate(startDate);
        modifiedBudget.setEndDate(endDate);
        budgetRepository.save(modifiedBudget);
    }

    public Budget getBudget(Long budgetID){
        return budgetRepository.getById(budgetID);
    }

    public void addBudget(String name, Date startDate, Date endDate){
        budgetRepository.save(new Budget(name, startDate, endDate));

    }

//    public List<CashFlow> getBudgetIncomes(Long budgetID){
//        return budgetRepository.findById(budgetID).get().getCashFlows().stream().filter(cashFlow -> cashFlow.getCashFlowAmount() > 0).toList();
//    }
//
//    public List<CashFlow> getBudgetExpenses(Long budgetID){
//        return budgetRepository.findById(budgetID).get().getCashFlows().stream().filter(cashFlow -> cashFlow.getCashFlowAmount() < 0).toList();
//    }

    public String displayBudget(Budget budget){
        String printedBudget = "";
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        printedBudget = "The Budget ID is: " + Long.toString(budget.getId()) + " and the name is: " + budget.getName() + " the start date : " + dateFormat.format(budget.getStartDate()) + " the end date : " + dateFormat.format(budget.getEndDate());

        return  printedBudget;
    }

}