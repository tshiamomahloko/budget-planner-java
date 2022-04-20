package com.javalevelup.budgetapp.Utils;

import com.javalevelup.budgetapp.Customer.Customer;
import com.javalevelup.budgetapp.CashFlow.CashFlowRepository;
import com.javalevelup.budgetapp.Budget.BudgetRepository;
import com.javalevelup.budgetapp.Customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class SaveToRepository {

    private final BudgetRepository budgetRepository;
    private final CustomerRepository userRepository;
    private final CashFlowRepository cashFlowRepository;
    private final PasswordEncoder passwordEncoder;

    public Customer addUser(Customer user) {

        log.info(user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Customer savedUser = userRepository.save(user);
        user.setId(savedUser.getId());
//        savedUser.setBudgets(saveBudgets(user));

        return user;
    }

//    public Collection<Budget> saveBudgets(User user) {
//        Collection<Budget> returnBudgets = new ArrayList<>();
//
//        Collection<Budget> saveBudgets = user.getBudgets();
//        log.info(saveBudgets.toString());
//        saveBudgets.forEach(budget -> {
//            Budget budgetHolder = new Budget(budget.getName(), budget.getStartDate(), budget.getEndDate(), user);
//            Budget savedBudget = budgetRepository.save(budgetHolder);
//              savedBudget.addCashFlowToBudget(saveBudgetComponent(budget.getBudgetComponents(), savedBudget));
//            returnBudgets.add(savedBudget);
//        });
//
//        return returnBudgets;
//    }

//    public Collection<CashFlow> saveBudgetComponent(Collection<CashFlow> budgetComponents, Budget budget) {
//        Collection<CashFlow> returnBudgets = new ArrayList<>();
//
//        log.info(budgetComponents.toString());
//        for (CashFlow cashFlow :
//                budgetComponents) {
//            CashFlow cashFlow1 = new CashFlow(cashFlow.getType(), budgetComponent.getName(), budgetComponent.getAmount(), budget);
//            returnBudgets.add(budgetComponentRepository.save(budgetComponentHolder));
//        }
//
//        return returnBudgets;
//    }
}

