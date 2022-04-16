package com.javalevelup.budgetApp.repositories;

import com.javalevelup.budgetApp.models.CashFlow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashFlowRepository
        extends JpaRepository<CashFlow, Long> {
}
