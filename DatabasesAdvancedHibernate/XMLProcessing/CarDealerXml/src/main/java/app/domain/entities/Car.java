package app.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {
    private String make;
    private String model;
    private Long travelledDistance;
    private List<Part> parts;

    public Car() {
    }

    @Column(name = "make")
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "travelled_distance")
    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cars_parts",
            joinColumns =
            @JoinColumn(name = "car_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "part_id", referencedColumnName = "id"))
    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
