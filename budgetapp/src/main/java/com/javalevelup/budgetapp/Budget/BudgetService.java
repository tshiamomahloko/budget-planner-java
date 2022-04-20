package com.javalevelup.budgetapp.Budget;

//import com.javalevelup.budgetapp.CashFlow.CashFlow;
//import com.javalevelup.budgetapp.User.User;
import com.javalevelup.budgetapp.Customer.Customer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

import static java.lang.Long.valueOf;

@AllArgsConstructor
@Service
public class BudgetService {
    @Autowired
    private final BudgetRepository budgetRepository;

    public List<Budget> getUserBudgets(Long userID){
        return budgetRepository.getCustomerBudgets((userID));
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

    public Budget getBudgetByID(Long budgetID){
        return budgetRepository.findById(budgetID).get();
    }

    public void addBudget(String name, Date startDate, Date endDate, Customer customer){
        budgetRepository.save(new Budget(name, startDate, endDate, customer));

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