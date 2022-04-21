package com.javalevelup.budgetapp.cashflow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/cashflows")
@AllArgsConstructor
@Slf4j
public class CashFlowController {
    private CashFlowService cashFlowService;

    @GetMapping(path="{username}")
    public List<CashFlow> getUserCashFlows(@PathVariable("username") String username, Principal principal){
        if (username.equals(principal.getName())) {
            return cashFlowService.getCustomerCashFlows(username);
        }
        throw new IllegalStateException(
                "User not authorized to access information");
    }

    @DeleteMapping(path="{username}/delete-cash-flow/{cashFlowId}")
    public void deleteCustomerCashFlowById(@PathVariable("username") String username, @PathVariable("cashFlowId") Long cashFlowId,Principal principal){
        if (username.equals(principal.getName())) {
            cashFlowService.removeCustomerCashFlowById(cashFlowId);
        } else throw new IllegalStateException(
                "Not authorized to delete this cash flow");
    }
   
}
