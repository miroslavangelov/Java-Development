package app.domain.entities;

import javax.persistence.*;

@Entity(name = "towns")
public class Town extends BaseEntity {
    private String name;
    private Country country;

    public Town(){
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "country_id")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
