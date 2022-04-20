package com.javalevelup.budgetapp;

import com.javalevelup.budgetapp.Budget.BudgetRepository;
import com.javalevelup.budgetapp.Budget.BudgetService;
import com.javalevelup.budgetapp.Customer.Customer;
import com.javalevelup.budgetapp.Customer.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BudgetappApplication {

	public static void main(String[] args) {

		SpringApplication.run(BudgetappApplication.class, args);

	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
