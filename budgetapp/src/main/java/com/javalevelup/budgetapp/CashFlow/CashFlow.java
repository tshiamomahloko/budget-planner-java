package com.javalevelup.budgetapp.CashFlow;

import com.javalevelup.budgetapp.Budget.Budget;
import com.javalevelup.budgetapp.User.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    private Double CashFlowAmount;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User userId;

    @ManyToMany(
            mappedBy = "cashFlows"
    )
private List<Budget> budgets = new ArrayList<>();

}
