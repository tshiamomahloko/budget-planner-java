package com.javalevelup.budgetapp.budget;

import com.javalevelup.budgetapp.cashflow.CashFlow;
import com.javalevelup.budgetapp.customer.Customer;
import com.javalevelup.budgetapp.customer.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final CustomerRepository customerRepository;

    public List<Budget> getCustomerBudgets(String username){
        return budgetRepository.findAllByCustomerUsername(username);
    }

    public void replicateBudget(Long budgetID){
        Budget budget = budgetRepository.findById(budgetID).get();
        budgetRepository.save(new Budget(budget.getName(), budget.getStartDate(), budget.getEndDate(), budget.getCustomer()));
    }

    public void updateBudget(Long budgetID, Budget budget){
        if (budgetRepository.findById(budgetID).isPresent()) {
            Budget modifiedBudget = budgetRepository.findById(budgetID).get();
            if (budget.getName() != null && !budget.getName().isEmpty()) {
                if (!modifiedBudget.getName().equals(budget.getName())) {
                    modifiedBudget.setName(budget.getName());
                }
            }
            if (budget.getStartDate() != null) {
                if (!modifiedBudget.getStartDate().equals(budget.getStartDate())) {
                    modifiedBudget.setStartDate(budget.getStartDate());
                }
            }
            if (budget.getEndDate() != null) {
                if (!modifiedBudget.getEndDate().equals(budget.getEndDate())) {
                    modifiedBudget.setEndDate(budget.getEndDate());
                }
            }
            budgetRepository.save(modifiedBudget);
        }
    }

    public Budget getBudgetByID(Long budgetID){
        if (budgetRepository.findById(budgetID).isPresent()) {
            return budgetRepository.findById(budgetID).get();
        }
        return null;
    }

    public void addBudget(String username, Budget budget){
        if(budget.getStartDate().isAfter(budget.getEndDate())){
            throw new IllegalStateException("The start date must be before end date.");
        }
  
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException(
                        String.format("User with username %s does not exist", username)));
        log.info(customer.toString());
        Budget savedBudget = budgetRepository.save(budget);
        customer.addBudgetToCustomer(savedBudget);
    }

    public void addCashflowToBudget(Long id, CashFlow cashFlow){
        try{
            Budget budget = getBudgetByID(id);
            budget.addCashFlowToBudget(cashFlow);
            budgetRepository.save(budget);
        }catch(Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    public void removeCashFlowFromBudget(Long id, CashFlow cashFlow){
        Budget budget = getBudgetByID(id);
        budget.removeCashFlowFromBudget(cashFlow);
        budgetRepository.save(budget);
    }


    public List<CashFlow> getBudgetIncomes(Long budgetID){
        return budgetRepository.findById(budgetID).get().getCashFlows().stream().filter(cashFlow -> cashFlow.getAmount() > 0).toList();
    }

    public List<CashFlow> getBudgetExpenses(Long budgetID){
        return budgetRepository.findById(budgetID).get().getCashFlows().stream().filter(cashFlow -> cashFlow.getAmount() < 0).toList();
    }

    public Double getBudgetBalance(Long budgetID){
        List<CashFlow> budgetCashFlow = Stream.of(getBudgetIncomes(budgetID), getBudgetExpenses(budgetID)).flatMap(Collection::stream).collect(Collectors.toList());
        Double balance = 0.0;
        for(CashFlow cf : budgetCashFlow){
            balance += cf.getAmount();
        }

        return balance;
    }



    public String displayBudget(Budget budget){
        String printedBudget = "";
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        printedBudget = "The Budget ID is: " + Long.toString(budget.getId()) + " and the name is: " + budget.getName() + " the start date : " + dateFormat.format(budget.getStartDate()) + " the end date : " + dateFormat.format(budget.getEndDate());

        return  printedBudget;
    }

}