package com.javalevelup.budgetApp.services;

import com.javalevelup.budgetApp.models.User;
import com.javalevelup.budgetApp.repositories.BudgetComponentRepository;
import com.javalevelup.budgetApp.repositories.BudgetRepository;
import com.javalevelup.budgetApp.repositories.UserRepository;
import com.javalevelup.budgetApp.utils.SaveToRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BudgetRepository budgetRepository;
    private final BudgetComponentRepository budgetComponentRepository;
    private final SaveToRepository saveToRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public User addUser(User user) {
        if (userRepository.isEmailPresent(user.getEmail()) > 0) {
            throw new IllegalStateException(
                    "Email already exists");
        } else if (userRepository.isUsernamePresent(user.getUsername()) > 0) {
            throw new IllegalStateException(
                    "Username already exists");
        }
        return saveToRepository.addUser(user);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        User userHolder = userRepository.save(user);
//        user.getBudgets().forEach(budget -> {
//            Budget saveBudget = new Budget(budget.getName(), budget.getStartDate(), budget.getEndDate(), userHolder);
//            Budget budgetHolder = budgetRepository.save(saveBudget);
//            for (BudgetComponent budgetComponent :
//                    budget.getBudgetComponents()) {
//                System.out.println(budgetComponent.getType());
//                BudgetComponent budgetComponent1 = new BudgetComponent(budgetComponent.getType(), budgetComponent.getName(), budgetComponent.getAmount(), budgetHolder);
////                budgetComponent1.setType(CashFlowType.valueOf());
//                budgetComponentRepository.save(budgetComponent1);
//            }
//        });
//        return userHolder;
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalStateException(
                    String.format("User with id %d does not exist", id));
        }
        userRepository.deleteById(id);
    }

    public void updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        String.format("User with id %d does not exist", id)));
        // TODO: updateUser logic

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User found in the database: {}", username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }
}
