package com.javalevelup.budgetApp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@ToString
@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "Budget")
@Table(name = "Budget")
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
    private Long id;

    private String name;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "budget")
    @ToString.Exclude
    private Collection<BudgetComponent> budgetComponents = new ArrayList<>();

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Budget(String name, LocalDate startDate, LocalDate endDate, User user) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }

    public Double getIncomeTotal() {
        AtomicReference<Double> incomeTotal = new AtomicReference<>(0.0);
        getBudgetComponents().stream()
                .filter((b) -> b.getType().name().toLowerCase().equals("income"))
                .forEach(n -> incomeTotal.updateAndGet(v -> v + n.getAmount()));
        return incomeTotal.get();
    }

    public Double getExpenseTotal() {
        AtomicReference<Double> expenseTotal = new AtomicReference<>(0.0);
        getBudgetComponents().stream()
                .filter((b) -> b.getType().name().toLowerCase().equals("expense"))
                .forEach(n -> expenseTotal.updateAndGet(v -> v + n.getAmount()));
        return expenseTotal.get();
    }

    public Double getTotal() {
        return getIncomeTotal() - getExpenseTotal();
    }
//    @ManyToMany(
//            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
//    )
//    @JoinTable(
//            name="budget_cash_flow",
//            joinColumns = @JoinColumn(
//                    name="budget_id",
//                    foreignKey = @ForeignKey(
//                            name = "budget_cash_flow_budget_id_fk"
//                    )
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name="cash_flow_id",
//                    foreignKey = @ForeignKey(
//                            name = "budget_cash_flow_cash_flow_id_fk"
//                    )
//            )
//    )
//    @ToString.Exclude
//    private List<CashFlow> cashFlows = new ArrayList<>();

//    public void addCashFlowToBudget(CashFlow cf){
//        if(!this.cashFlows.contains(cf)){
//            this.cashFlows.add(cf);
//            cf.getBudgets().add(this);
//        }
//    }
//
//    public void removeCashFlowFromBudget(CashFlow cf) {
//        this.cashFlows.remove(cf);
//        cf.getBudgets().remove(this);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Budget budget = (Budget) o;
        return id != null && Objects.equals(id, budget.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
