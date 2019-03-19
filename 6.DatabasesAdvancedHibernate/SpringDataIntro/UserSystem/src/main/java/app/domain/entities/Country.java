package app.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "countries")
public class Country extends BaseEntity {
    private String name;
    private Set<Town> towns;

    public Country(){
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "country")
    public Set<Town> getTowns() {
        return towns;
    }

    public void setTowns(Set<Town> towns) {
        this.towns = towns;
    }
}
