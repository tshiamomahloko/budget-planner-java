package com.javalevelup.budgetapp.Customer;

import com.javalevelup.budgetapp.CashFlow.CashFlowRepository;
import com.javalevelup.budgetapp.Budget.BudgetRepository;
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
import java.util.Objects;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class CustomerService implements UserDetailsService {

    private final CustomerRepository userRepository;
    private final BudgetRepository budgetRepository;
    private final CashFlowRepository cashFlowRepository;
    private final SaveToRepository saveToRepository;
    private final PasswordEncoder passwordEncoder;

    public Customer getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException(
                        String.format("User with username %s does not exist", username)));
    }

    public void addUser(Customer user) {
        if (user.getEmail() == null || user.getEmail().isEmpty() || userRepository.isEmailPresent(user.getEmail()) > 0) {
            if (user.getEmail() == null || user.getEmail().isEmpty()) {
                throw new IllegalStateException("Email not provided");
            } else throw new IllegalStateException("Email already exists");
        } else if (user.getUsername() == null || user.getUsername().isEmpty() ||userRepository.isUsernamePresent(user.getUsername()) > 0) {
            if (user.getUsername() == null || user.getUsername().isEmpty()) {
                throw new IllegalStateException("Username not provided");
            } else throw new IllegalStateException("Username already exists");
        } else {
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                throw new IllegalStateException("Password not provided");
            }
            saveToRepository.addUser(user);
        }
    }

    public void deleteUser(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new IllegalStateException(
                    String.format("User with username %s does not exist", username));
        }
        userRepository.deleteByUsername(username);
    }

    public void updateUser(String username, Customer updatedUser) {
        Customer user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException(
                        String.format("User with username %s does not exist", username)));
        if (updatedUser.getUsername() != null && !updatedUser.getUsername().isEmpty()) {
            if (!Objects.equals(updatedUser.getUsername(), user.getUsername())) {
                user.setUsername(updatedUser.getUsername());
            }
        }
        if (updatedUser.getEmail() != null && !updatedUser.getEmail().isEmpty()) {
            if (userRepository.isEmailPresent(updatedUser.getEmail()) > 0) {
                throw new IllegalStateException(
                        String.format("Email %s already exists", updatedUser.getEmail()));
            }
            user.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer user = userRepository.findByUsername(username)
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
