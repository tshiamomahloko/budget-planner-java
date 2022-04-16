package com.javalevelup.budgetApp.repositories;

import com.javalevelup.budgetApp.models.BudgetComponent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetComponentRepository
        extends JpaRepository<BudgetComponent, Long> {
}
