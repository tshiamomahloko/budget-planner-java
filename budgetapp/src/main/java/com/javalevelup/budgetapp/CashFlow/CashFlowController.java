package com.javalevelup.budgetapp.CashFlow;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/cashFlows")
@AllArgsConstructor
public class CashFlowController {
    private CashFlowService cashFlowService;

    @GetMapping
    public List<CashFlow> getAllCashFlows(){
        return cashFlowService.getAllCashFlows();
    }
    @RequestMapping(path="/cashFlows/{id}")
    public CashFlow getCashFlowById(Long id){
        return cashFlowService.getCashFlowById(id);
    }
    @RequestMapping(method=RequestMethod.POST, value = "/cashFlows/{cashflowId}")
    public boolean addCashFlow(@RequestBody CashFlow cashFlow){
        return cashFlowService.addCashFlow(cashFlow);
    }
    @RequestMapping(method=RequestMethod.DELETE, value = "/cashFlows/{cashflowId}")
    public boolean deleteCashFlow(@PathVariable Long id){
        return cashFlowService.removeCashFlow(id);
    }
    @RequestMapping(method=RequestMethod.GET, value = "/cashFlows/count")
    public Long getCount(){
        return cashFlowService.getCount();
    }
   
}
