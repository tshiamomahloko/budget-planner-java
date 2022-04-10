package com.javalevelup.budgetapp.Budget;

import lombok.*;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.sql.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table
@Check(
        constraints = "startDate < endDate"
)
public class Budget {
    @Id
    @SequenceGenerator(
            name = "budget_sequence",
            sequenceName = "budget_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "student_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long budgetID;

    @Column(name="startDate")
    private Date startDate;

    @Column(name="endDate")
    private Date endDate;

    //todo: many to one transactions reference bridging table

//    @ManyToMany(
//
//    )
//    private List<Transaction> transactions = new ArrayList<>();

}
