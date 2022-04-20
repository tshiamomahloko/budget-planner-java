package com.javalevelup.budgetapp.Budget;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javalevelup.budgetapp.CashFlow.CashFlow;
import com.javalevelup.budgetapp.User.Customer;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@ToString
@Getter
@Setter
@RequiredArgsConstructor
@Entity(name="Budget")
@Table(name="Budget")
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

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Customer user;

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
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


    @JsonBackReference
    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            foreignKey = @ForeignKey(
                    name = "budget_customer_id_fk"
            )
    )
    private Customer customer;

    public Budget(String name, Date startDate, Date endDate, Customer customer){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
    }

    public void addCashFlowToBudget(CashFlow cf){
        if(!this.cashFlows.contains(cf)){
            this.cashFlows.add(cf);
            cf.getBudgets().add(this);
        }
    }

    public void removeCashFlowFromBudget(CashFlow cf) {
        this.cashFlows.remove(cf);
        cf.getBudgets().remove(this);
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

