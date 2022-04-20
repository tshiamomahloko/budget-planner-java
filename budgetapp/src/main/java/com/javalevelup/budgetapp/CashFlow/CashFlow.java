package com.javalevelup.budgetapp.CashFlow;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.javalevelup.budgetapp.Budget.Budget;
import com.javalevelup.budgetapp.Customer.Customer;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name="CashFlow")
@Table(
        name="cashflow"
)
public class CashFlow {
    @Id
    @SequenceGenerator(
            name = "cash_flow_sequence",
            sequenceName = "cash_flow_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "cash_flow_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @Column(
            name="cashflow_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;
    @Column(
            name="cashflow_amount",
            nullable = false,
            columnDefinition = "decimal"
    )
    private Double amount;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Customer customer;

    @JsonBackReference
    @ManyToMany(
            mappedBy = "cashFlows"
    )
    @ToString.Exclude
    private List<Budget> budgets = new ArrayList<>();

    public CashFlow(String name, Double amount) {
        this.name = name;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CashFlow cashFlow = (CashFlow) o;
        return id != null && Objects.equals(id, cashFlow.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
