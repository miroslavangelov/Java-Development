package app.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {
    private Car car;
    private Customer customer;
    private Double discount;

    public Sale() {
    }

    @OneToOne(targetEntity = Car.class)
    @JoinColumn(name = "car_id")
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
