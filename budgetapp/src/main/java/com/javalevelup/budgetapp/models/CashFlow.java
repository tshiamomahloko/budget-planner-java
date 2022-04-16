package com.javalevelup.budgetApp.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@ToString
@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "CashFlow")
@Table(name = "cash_flow")
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

//    @ManyToMany(mappedBy = "cashFlows")
//    @ToString.Exclude
//    private List<Budget> budgets = new ArrayList<>();

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
