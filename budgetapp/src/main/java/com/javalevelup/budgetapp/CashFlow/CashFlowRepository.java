package com.javalevelup.budgetapp.CashFlow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashFlowRepository  extends JpaRepository<CashFlow, Long>{
    List<CashFlow> findByCustomerId(Long customerID);
//    @Query("SELECT b FROM cashflow WHERE b.userID = ?1")
//    List<Budget> findBudgetsByUserID(Long userID);
}
