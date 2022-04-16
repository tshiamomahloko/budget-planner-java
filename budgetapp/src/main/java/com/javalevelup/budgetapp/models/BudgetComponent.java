package com.javalevelup.budgetApp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@ToString
@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "BudgetComponent")
@Table(name = "BudgetComponent")
public class BudgetComponent {

    @Id
    @SequenceGenerator(
            name = "budget_component_sequence",
            sequenceName = "budget_component_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "budget_component_sequence"
    )
    private Long id;

    private CashFlowType type;
    private String name;
    private Long amount;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id")
    @ToString.Exclude
    private Budget budget;

    public BudgetComponent(CashFlowType type, String name, Long amount, Budget budget) {
        this.type = type;
        this.name = name;
        this.amount = amount;
        this.budget = budget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BudgetComponent that = (BudgetComponent) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
