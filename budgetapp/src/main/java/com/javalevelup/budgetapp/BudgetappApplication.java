package com.javalevelup.budgetapp;

import com.javalevelup.budgetapp.Budget.BudgetRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.javalevelup.budgetapp.Budget.Budget;
import com.javalevelup.budgetapp.CashFlow.CashFlow;

import java.sql.Date;

@SpringBootApplication
public class BudgetappApplication {

	public static void main(String[] args) {

		SpringApplication.run(BudgetappApplication.class, args);

	}
	@Bean
	CommandLineRunner commandLineRunner(BudgetRepository br) {
		return args -> {
			Faker faker = new Faker();

			Date startDate = new Date(1649769300000L);
			Date endDate = new Date(1651324500000L);
			Budget budget = new Budget("April", new Date(1649769300000L), new Date(1651324500000L));
			Budget budget2 = new Budget("May", new Date(1651324500000L), new Date(1653916500000L));

			CashFlow cashFlow = new CashFlow();
			CashFlow cashFlow2 = new CashFlow();
			budget.addCashFlowToBudget(cashFlow);
			budget2.addCashFlowToBudget(cashFlow2);
			br.save(budget);
			br.save(budget2);

			budget2.addCashFlowToBudget(cashFlow);

			br.save(budget2);

		};
	}
}
