package com.javalevelup.budgetApp.services;

import com.javalevelup.budgetApp.models.BudgetComponent;
import com.javalevelup.budgetApp.repositories.BudgetComponentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class BudgetComponentService {

    private final BudgetComponentRepository budgetComponentRepository;

    public BudgetComponent getBudgetComponent(Long id) {
        return budgetComponentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        String.format("Budget component with id %d does not exist", id)));
    }

    public BudgetComponent addNewBudgetComponent(BudgetComponent budgetComponent) {
        return budgetComponentRepository.save(budgetComponent);
    }

    public void deleteBudgetComponent(Long id) {
        if (!budgetComponentRepository.existsById(id)) {
            throw new IllegalStateException(
                    String.format("Budget component with id %d does not exist", id));
        }
        budgetComponentRepository.deleteById(id);
    }

    public void updateBudgetComponent(Long id, BudgetComponent updatedBudgetComponent) {
        BudgetComponent budgetComponent = budgetComponentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        String.format("Budget component with id %d does not exist", id)));
        // TODO: updateBudgetComponent logic
    }
}
