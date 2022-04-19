package com.javalevelup.budgetapp.Budget;

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
    private List<Budget> allBudgets;

    public List<Budget> getAllBudgets(){
        this.allBudgets = new ArrayList<>();
        budgetRepository.findAll()
                .forEach(allBudgets::add);

        return  allBudgets;
    }

//    public List<Budget> getCustomerBudgets(int customerID){
//        getAllBudgets();
//        List<Budget> newBudgetList = new ArrayList<>();
//        for (Budget singleBudget : allBudgets) {
//            if (singleBudget.getCustomerID() == (valueOf(customerID))){
//                newBudgetList.add(singleBudget);
//            }
//        }
//        allBudgets = newBudgetList;
//        return allBudgets;
//    }



    public void replicateBudget(Long budgetID){
        Budget budgetOriginal = budgetRepository.findById(budgetID).get();

        budgetRepository.save(new Budget(budgetOriginal.getName(), budgetOriginal.getStartDate(), budgetOriginal.getEndDate()));

        allBudgets = getAllBudgets();
    }

    public void modifyBudget(Long budgetID, String budgetName, Date startDate, Date endDate){
        Budget modifiedBudget = budgetRepository.findById(budgetID).get();
        modifiedBudget.setName(budgetName);
        modifiedBudget.setStartDate(startDate);
        modifiedBudget.setEndDate(endDate);

        budgetRepository.save(modifiedBudget);
        allBudgets = getAllBudgets();
    }

//    public void deleteBudget(Long budgetID){
//        budgetRepository.deleteById(budgetID);
//        allBudgets = getAllBudgets();
//    }

    public Budget getOneBudget(Long budgetID){
        for (Budget budget : allBudgets) {
            if (budget.getId() == (budgetID)){
                return budget;
            }
        }
        return  null;
    }

    public void addBudget(String name, Date startDate, Date endDate){
        budgetRepository.save(new Budget(name, startDate, endDate));
        allBudgets = getAllBudgets();
    }

    public String displayBudget(Budget budget){
        String printedBudget = "";
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        for (Budget singleBudget : allBudgets) {
            if (singleBudget.equals(budget)){
                printedBudget = "The Budget ID is: " + Long.toString(budget.getId()) + " and the name is: " + budget.getName() + " the start date : " + dateFormat.format(budget.getStartDate()) + " the end date : " + dateFormat.format(budget.getEndDate());
            }
        }
        return  printedBudget;
    }

    public String displayAllBudgets(){
        String printedBudget = "";
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        for (Budget singleBudget : allBudgets) {
                printedBudget = printedBudget +  "The Budget ID is: " + Long.toString(singleBudget.getId()) + " and the name is: " + singleBudget.getName() + " the start date : " + dateFormat.format(singleBudget.getStartDate()) + " the end date : " + dateFormat.format(singleBudget.getEndDate()) + "\n" ;
        }

        return  printedBudget;

    }
}
