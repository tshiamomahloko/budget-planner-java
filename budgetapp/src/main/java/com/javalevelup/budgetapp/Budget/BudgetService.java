package com.javalevelup.budgetapp.Budget;

import com.javalevelup.budgetapp.CashFlow.CashFlow;
import com.javalevelup.budgetapp.Customer.Customer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class BudgetService {
    @Autowired
    private final BudgetRepository budgetRepository;

    public List<Budget> getCustomerBudgets(Long customerID){
        return budgetRepository.getCustomerBudgets((customerID));
    }

    public void replicateBudget(Long budgetID){
        Budget budget = budgetRepository.findById(budgetID).get();
        budgetRepository.save(new Budget(budget.getName(), budget.getStartDate(), budget.getEndDate(), budget.getCustomer()));
    }

    public void modifyBudget(Long budgetID, String budgetName, String startDate, String endDate){
        Budget modifiedBudget = budgetRepository.findById(budgetID).get();
        modifiedBudget.setName(budgetName);
        modifiedBudget.setStartDate(LocalDate.parse(startDate));
        modifiedBudget.setEndDate(LocalDate.parse(endDate));
        budgetRepository.save(modifiedBudget);
    }

    public Budget getBudgetByID(Long budgetID){
        return budgetRepository.findById(budgetID).get();
    }

    public void addBudget(Budget budget){
        budgetRepository.save(budget);
    }

    public void addCashflowToBudget(Long id, CashFlow cashFlow){
        Budget budget = getBudgetByID(id);
        budget.addCashFlowToBudget(cashFlow);
        budgetRepository.save(budget);
    }


    public List<CashFlow> getBudgetIncomes(Long budgetID){
        return budgetRepository.findById(budgetID).get().getCashFlows().stream().filter(cashFlow -> cashFlow.getAmount() > 0).toList();
    }

    public List<CashFlow> getBudgetExpenses(Long budgetID){
        return budgetRepository.findById(budgetID).get().getCashFlows().stream().filter(cashFlow -> cashFlow.getAmount() < 0).toList();
    }

    public String displayBudget(Budget budget){
        String printedBudget = "";
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        printedBudget = "The Budget ID is: " + Long.toString(budget.getId()) + " and the name is: " + budget.getName() + " the start date : " + dateFormat.format(budget.getStartDate()) + " the end date : " + dateFormat.format(budget.getEndDate());

        return  printedBudget;
    }

}