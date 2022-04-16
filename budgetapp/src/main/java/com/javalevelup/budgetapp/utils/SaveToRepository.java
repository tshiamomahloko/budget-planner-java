package com.javalevelup.budgetApp.utils;

import com.javalevelup.budgetApp.models.Budget;
import com.javalevelup.budgetApp.models.BudgetComponent;
import com.javalevelup.budgetApp.models.User;
import com.javalevelup.budgetApp.repositories.BudgetComponentRepository;
import com.javalevelup.budgetApp.repositories.BudgetRepository;
import com.javalevelup.budgetApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class SaveToRepository {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final BudgetComponentRepository budgetComponentRepository;
    private final PasswordEncoder passwordEncoder;

    public User addUser(User user) {

        log.info(user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        user.setId(savedUser.getId());
        savedUser.setBudgets(saveBudgets(user));

        return savedUser;
    }

    public Collection<Budget> saveBudgets(User user) {
        Collection<Budget> returnBudgets = new ArrayList<>();

        Collection<Budget> saveBudgets = user.getBudgets();
        log.info(saveBudgets.toString());
        saveBudgets.forEach(budget -> {
            Budget budgetHolder = new Budget(budget.getName(), budget.getStartDate(), budget.getEndDate(), user);
            Budget savedBudget = budgetRepository.save(budgetHolder);
            savedBudget.setBudgetComponents(saveBudgetComponent(budget.getBudgetComponents(), savedBudget));
            returnBudgets.add(savedBudget);
        });

        return returnBudgets;
    }

    public Collection<BudgetComponent> saveBudgetComponent(Collection<BudgetComponent> budgetComponents, Budget budget) {
        Collection<BudgetComponent> returnBudgets = new ArrayList<>();

        log.info(budgetComponents.toString());
        for (BudgetComponent budgetComponent :
                budgetComponents) {
            BudgetComponent budgetComponentHolder = new BudgetComponent(budgetComponent.getType(), budgetComponent.getName(), budgetComponent.getAmount(), budget);
            returnBudgets.add(budgetComponentRepository.save(budgetComponentHolder));
        }

        return returnBudgets;
    }
}
