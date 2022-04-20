package com.javalevelup.budgetapp;

import com.javalevelup.budgetapp.Budget.BudgetRepository;
import com.javalevelup.budgetapp.Budget.BudgetService;
import com.javalevelup.budgetapp.Customer.Customer;
import com.javalevelup.budgetapp.Customer.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.javalevelup.budgetapp.Budget.Budget;
import com.javalevelup.budgetapp.CashFlow.CashFlow;

import java.sql.Date;
import java.util.List;

@SpringBootApplication
public class BudgetappApplication {

	public static void main(String[] args) {

		SpringApplication.run(BudgetappApplication.class, args);

	}
	@Bean
	CommandLineRunner commandLineRunner(BudgetRepository br, BudgetService bs, UserRepository cr) {
		return args -> {
			Faker faker = new Faker();
			Customer customer = new Customer("Tori");
			cr.save(customer);

			Budget budget = new Budget("April", new Date(1649769300000L), new Date(1651324500000L), customer);
			Budget budget2 = new Budget("May", new Date(1651324500000L), new Date(1653916500000L), customer);

			CashFlow cashFlow = new CashFlow("St Lucia", 1021020.0 );
			CashFlow cashFlow2 = new CashFlow("Cats cats cats", -1212151.50);
			budget.addCashFlowToBudget(cashFlow);
			budget2.addCashFlowToBudget(cashFlow2);

			br.save(budget);
			br.save(budget2);

			budget2.addCashFlowToBudget(cashFlow);
			br.save(budget2);

			List<Budget> b = bs.getUserBudgets(1L);

			System.out.println("TIME FOR SOME CONSOLE LOGS BITCHES!");
			b.forEach(BHDJHS -> System.out.println(bs.displayBudget(BHDJHS)));
			bs.replicateBudget(1L);
			bs.modifyBudget(3L,"Woof", new Date(1651324500000L), new Date(1651324500000L));

			System.out.println("TIME FOR SOME CONSOLE LOGS BITCHES!");
			bs.getUserBudgets(1L).forEach(BHDJHS -> System.out.println(bs.displayBudget(BHDJHS)));
		};
	}
}
