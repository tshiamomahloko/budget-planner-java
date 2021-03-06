package com.javalevelup.budgetapp.budget;

import com.fasterxml.jackson.annotation.*;

import com.javalevelup.budgetapp.cashflow.CashFlow;
import com.javalevelup.budgetapp.customer.Customer;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;


@ToString
@Getter
@Setter
@RequiredArgsConstructor
@Entity(name="Budget")
@Table(name="Budget")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Slf4j
public class Budget {
    @Id
    @SequenceGenerator(
            name = "budget_sequence",
            sequenceName = "budget_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "budget_sequence",
            strategy = GenerationType.SEQUENCE
    )
    @Column(name="budget_id")
    private Long id;

    @NotBlank
    @NotNull
    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @NotNull
    @Column(
            columnDefinition="DATE",
            nullable = false
    )
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @NotNull
    @Column(
            columnDefinition="DATE",
            nullable = false
    )
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;

    @ManyToMany(
            cascade = {CascadeType.ALL}
    )
    @JoinTable(
            name="budget_cash_flow",
            joinColumns = @JoinColumn(
                    name="budget_id",
                    foreignKey = @ForeignKey(
                            name = "budget_cash_flow_budget_id_fk"
                    )
            ),
            inverseJoinColumns = @JoinColumn(
                    name="cash_flow_id",
                    foreignKey = @ForeignKey(
                            name = "budget_cash_flow_cash_flow_id_fk"
                    )
            )
    )
    @ToString.Exclude
    private List<CashFlow> cashFlows = new ArrayList<>();


    @ManyToOne
    @JsonIgnore
    @JoinColumn(
            name = "customer_id",
            foreignKey = @ForeignKey(
                    name = "budget_customer_id_fk"
            )
    )
    private Customer customer;

    public Budget(String name, LocalDate startDate, LocalDate endDate, Customer customer){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
    }

    public Double getIncomeTotal() {
        AtomicReference<Double> incomeTotal = new AtomicReference<>(0.0);
        getCashFlows().stream()
                .filter((b) -> b.getAmount() > 0)
                .forEach(n -> incomeTotal.updateAndGet(v -> v + n.getAmount()));
        return incomeTotal.get();
    }

    public Double getExpenseTotal() {
        AtomicReference<Double> expenseTotal = new AtomicReference<>(0.0);
        getCashFlows().stream()
                .filter((b) -> b.getAmount() < 0)
                .forEach(n -> expenseTotal.updateAndGet(v -> v + n.getAmount()));
        return expenseTotal.get();
    }

    public void addCashFlowToBudget(CashFlow cf){
        if(!this.cashFlows.contains(cf)){
            this.cashFlows.add(cf);
            cf.getBudgets().add(this);
            cf.setCustomer(this.customer);
        }
    }

    public void removeCashFlowFromBudget(CashFlow cf) {
        cf.getBudgets().remove(this);
        cf.setCustomer(null);
        this.cashFlows.remove(cf);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Budget budget = (Budget) o;
        return id != null && Objects.equals(id, budget.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

