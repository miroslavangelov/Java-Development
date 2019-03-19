package hiberspring.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {
    private String firstName;
    private String lastName;
    private String position;
    private EmployeeCard card;
    private Branch branch;

    public Employee() {
    }

    @NotNull
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @Column(name = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @NotNull
    @OneToOne(targetEntity = EmployeeCard.class)
    @JoinColumn(name = "card_id", unique = true)
    public EmployeeCard getCard() {
        return card;
    }

    public void setCard(EmployeeCard card) {
        this.card = card;
    }

    @NotNull
    @ManyToOne(targetEntity = Branch.class)
    @JoinColumn(name = "branch_id")
    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
