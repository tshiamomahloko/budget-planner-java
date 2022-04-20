package com.javalevelup.budgetapp.Budget;

import com.javalevelup.budgetapp.CashFlow.CashFlow;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

@Entity(name="Budget")
@Table(name="budget")
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
            name="budget_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @NotNull
    @Column(
            name="start_date",
            columnDefinition="DATE",
            nullable = false
    )
    private Date startDate;

    @NotNull
    @Column(
            name="end_date",
            columnDefinition="DATE",
            nullable = false
    )
    private Date endDate;

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
    private List<CashFlow> cashFlows = new ArrayList<>();

    public Budget(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
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

}
