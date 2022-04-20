package com.javalevelup.budgetapp.Budget;

import com.amazonaws.services.identitymanagement.model.User;
import com.javalevelup.budgetapp.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BudgetRepository
        extends JpaRepository<Budget, Long>{
    //List<Budget> findAllByUser(Customer customer);

    @Query("select b from Budget b where b.customer.id = ?1")
    List<Budget> getCustomerBudgets(Long id);
//    @Query("SELECT b FROM Budget WHERE b.userID = ?1")
//    List<Budget> findBudgetsByUserID(Long userID);

//    @Query("SELECT b from Budget where b.userID = ?1")
//    List<Budget> findBudgetsByUserID(Long userID);
}