package com.javalevelup.budgetapp.CashFlow;

import com.javalevelup.budgetapp.Budget.Budget;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name="CashFlow")
@Table(
        name="cashFlow"
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

    @ManyToMany(
            mappedBy = "cashFlows"
    )
private List<Budget> budgets = new ArrayList<>();

public List<Budget> getBudgets() {
  return budgets;
}

}
