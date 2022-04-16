package com.javalevelup.budgetApp.repositories;

import com.javalevelup.budgetApp.models.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository
        extends JpaRepository<Budget, Long> {
}
