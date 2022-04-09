package com.javalevelup.budgetapp.Budget;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BudgetRepository
        extends JpaRepository<Budget, Long>{
    @Query("SELECT b FROM Budget WHERE b.userID = ?1")
    List<Budget> findBudgetsByUserID(Long userID);
}
