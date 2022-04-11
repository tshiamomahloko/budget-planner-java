package com.javalevelup.budgetapp.Budget;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BudgetService {
    private final BudgetRepository budgetRepository;

    public List<Budget> getAllBudgets(){
        return budgetRepository.findAll();
    }
}
