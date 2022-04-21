package com.javalevelup.budgetapp.customer;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.javalevelup.budgetapp.budget.Budget;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "Users")
@Table(
        name = "Users",
        uniqueConstraints = {
                @UniqueConstraint(name = "UniqueEmail", columnNames = "email"),
                @UniqueConstraint(name = "UniqueUsername", columnNames = "username")
        }
)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Customer implements Serializable {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;

    private String username;
    private String email;
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    @ToString.Exclude
    private List<Budget> budgets = new ArrayList<>();

    public Customer(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void addBudgetToCustomer(Budget budget) {
        if (!this.budgets.contains(budget)) {
            this.budgets.add(budget);
            budget.setCustomer(this);
        }
    }

    public void removeBudgetFromCustomer(Budget budget) {
        this.budgets.remove(budget);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer user = (Customer) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
