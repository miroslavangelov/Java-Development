package mostwanted.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "districts")
public class District extends BaseEntity {
    private String name;
    private Town town;

    public District() {
    }

    @NotNull
    @Column(name = "name", unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(targetEntity = Town.class)
    @JoinColumn(name = "town_id")
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
