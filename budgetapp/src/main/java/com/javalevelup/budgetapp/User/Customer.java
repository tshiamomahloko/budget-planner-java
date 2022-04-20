package com.javalevelup.budgetapp.User;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.javalevelup.budgetapp.Budget.Budget;
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

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @ToString.Exclude
    private Collection<Budget> budgets = new ArrayList<>();

    public Customer(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
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
