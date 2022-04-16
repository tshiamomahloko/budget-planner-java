package com.javalevelup.budgetApp.services;

import com.javalevelup.budgetApp.models.Budget;
import com.javalevelup.budgetApp.repositories.BudgetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class BudgetService {

    private final BudgetRepository budgetRepository;

    public Budget getBudget(Long id) {
        return budgetRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        String.format("Budget with id %d does not exist", id)));
    }

    public Budget addNewBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    public void deleteBudget(Long id) {
        if (!budgetRepository.existsById(id)) {
            throw new IllegalStateException(
                    String.format("Budget with id %d does not exist", id));
        }
        budgetRepository.deleteById(id);
    }

    public void updateBudget(Long id, Budget updatedBudget) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        String.format("Budget with id %d does not exist", id)));
        // TODO: updateBudget logic
    }
}
