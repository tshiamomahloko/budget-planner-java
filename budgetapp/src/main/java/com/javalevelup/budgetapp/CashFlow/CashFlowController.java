package com.javalevelup.budgetapp.CashFlow;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/cashflows")
@AllArgsConstructor
public class CashFlowController {
    private CashFlowService cashFlowService;

    @GetMapping(path="{customerId}")
    public List<CashFlow> getUserCashFlows(@PathVariable("customerId") Long customerId){
        return cashFlowService.getCustomerCashFlows(customerId);
    }

    @GetMapping(path="{customerId}/{budgetId}")
    public Optional<CashFlow> getCustomerCashFlowById(@PathVariable("customerId") Long customerId, @PathVariable("budgetId") Long budgetId){
        return cashFlowService.getCustomerCashFlowById(customerId, budgetId);
    }

    @DeleteMapping(path="{cashFlowId}")
    public ResponseEntity<String> deleteCustomerCashFlowById(@PathVariable("cashFlowId") Long cashFlowId){
        return cashFlowService.removeCustomerCashFlowById(cashFlowId);
    }
   
}
