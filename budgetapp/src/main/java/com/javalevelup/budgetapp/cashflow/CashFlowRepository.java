package com.javalevelup.budgetapp.cashflow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashFlowRepository  extends JpaRepository<CashFlow, Long>{
    List<CashFlow> findByCustomerId(Long customerID);

    List<CashFlow> findByCustomerUsername(String username);
}
