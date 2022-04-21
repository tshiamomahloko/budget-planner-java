package com.javalevelup.budgetapp.Budget;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BudgetRepository
        extends JpaRepository<Budget, Long>{

    List<Budget> findAllByCustomerUsername(String username);

}