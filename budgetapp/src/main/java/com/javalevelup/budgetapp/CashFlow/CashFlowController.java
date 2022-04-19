package com.javalevelup.budgetapp.CashFlow;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/cashflows")
@AllArgsConstructor
public class CashFlowController {
    private CashFlowService cashFlowService;

    @GetMapping
    public List<CashFlow> getAllCashFlows(){
        return cashFlowService.getAllCashFlows();
    }
    @GetMapping(value="/{id}")
    public Optional<CashFlow> getCashFlowById(Long id){
        return cashFlowService.getCashFlowById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public boolean addCashFlow(@RequestBody CashFlow cashFlow){
        return cashFlowService.addCashFlow(cashFlow);
    }
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteCashFlow(@PathVariable Long id){
        return cashFlowService.removeCashFlow(id);
    }
    @GetMapping(value = "/count")
    public Long getCount(){
        return cashFlowService.getCount();
    }
   
}
