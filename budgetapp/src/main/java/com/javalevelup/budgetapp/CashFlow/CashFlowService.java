package com.javalevelup.budgetapp.CashFlow;

import com.javalevelup.budgetapp.Budget.Budget;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CashFlowService  {
    @Autowired
    private CashFlowRepository cashFlowRepository;

    public List<CashFlow> getAllCashFlows(){
        List<CashFlow> cashFlows = new ArrayList<>();
        cashFlowRepository.findAll()
        .forEach(cashFlows::add);
       return cashFlows;
    }

    public CashFlow addCashFlow(CashFlow cashFlow){
        return cashFlowRepository.save(cashFlow);
    }

    public List<CashFlow> getCustomerCashFlows(String username){
        return cashFlowRepository.findByCustomerUsername(username);
    }

//    public Optional<CashFlow> getCustomerCashFlowById(Long customerId, Long budgetId){
//        return  getCustomerCashFlows(customerId).stream()
//                .filter(cf -> cf.getId().equals(budgetId))
//                .findFirst();
//    }

    public void removeCustomerCashFlowById(Long cashFlowId) {
        try{
            Optional<CashFlow> cashflow = cashFlowRepository.findById(cashFlowId);
            List<Budget> budgets = new ArrayList<>();
            cashflow.ifPresent(cf -> budgets.addAll(cf.getBudgets()));
            budgets.stream().forEach(b -> b.removeCashFlowFromBudget(cashflow.get()));
            cashFlowRepository.deleteById(cashFlowId);
        }catch(Exception e){
             throw new IllegalStateException(e.getMessage());
        }

    }
}
