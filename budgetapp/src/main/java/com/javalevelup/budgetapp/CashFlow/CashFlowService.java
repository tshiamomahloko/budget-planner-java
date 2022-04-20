package com.javalevelup.budgetapp.CashFlow;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Optional<CashFlow> getCashFlowById(Long id){
        return  cashFlowRepository.findById(id);
    }

    public CashFlow addCashFlow(CashFlow cashFlow){
        return cashFlowRepository.save(cashFlow);
    }
    public boolean removeCashFlow(Long id){
        boolean isRemoved = false;
        var cashFlow = cashFlowRepository.getById(id);
        if(cashFlow != null){
            try {
                cashFlowRepository.delete(cashFlow);
            } catch(Exception e) {
                e.printStackTrace();
                isRemoved = true;
            }
        }
        return isRemoved;
    }
    public Long getCount(){
        return cashFlowRepository.count();
    }

}
