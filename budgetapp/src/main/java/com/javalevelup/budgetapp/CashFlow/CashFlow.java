package com.javalevelup.budgetapp.CashFlow;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
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

    @JsonIgnore
    @ManyToMany(
            mappedBy = "cashFlows"
    )
    private List<Budget> budgets = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private Customer customer;

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
