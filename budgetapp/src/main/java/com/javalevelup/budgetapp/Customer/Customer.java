package com.javalevelup.budgetapp.Customer;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.javalevelup.budgetapp.Budget.Budget;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name="Customer")
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name="customer_id")
    private Long id;

    @NotBlank
    @NotNull
    @Column(
            name="customer_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String username;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private List<Budget> budgets = new ArrayList<>();
}

