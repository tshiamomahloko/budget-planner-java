package com.javalevelup.budgetapp.Budget;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BudgetRepository
        extends JpaRepository<Budget, Long>{
//    @Query("SELECT b FROM Budget WHERE b.userID = ?1")
//    List<Budget> findBudgetsByUserID(Long userID);
}
