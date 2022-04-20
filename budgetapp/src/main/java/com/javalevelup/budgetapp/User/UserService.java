package com.javalevelup.budgetapp.User;

import com.javalevelup.budgetapp.User.User;
import com.javalevelup.budgetapp.CashFlow.CashFlowRepository;
import com.javalevelup.budgetapp.Budget.BudgetRepository;
import com.javalevelup.budgetapp.User.UserRepository;
import com.javalevelup.budgetapp.Utils.SaveToRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final CashFlowRepository cashFlowRepository;
    private final SaveToRepository saveToRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException(
                        String.format("User with username %s does not exist", username)));
    }

    public void addUser(User user) {
        if (userRepository.isEmailPresent(user.getEmail()) > 0) {
            throw new IllegalStateException(
                    "Email already exists");
        } else if (userRepository.isUsernamePresent(user.getUsername()) > 0) {
            throw new IllegalStateException(
                    "Username already exists");
        }
        saveToRepository.addUser(user);
    }

    public void deleteUser(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new IllegalStateException(
                    String.format("User with username %s does not exist", username));
        }
        userRepository.deleteByUsername(username);
    }

    public void updateUser(String username, User updatedUser) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException(
                        String.format("User with username %s does not exist", username)));
        // TODO: updateUser logic

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException(
                        String.format("User with username %s does not exist", username)));

        if (user == null) {
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User found in the database: {}", username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }
}
