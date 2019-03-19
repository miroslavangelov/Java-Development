package alararestaurant.domain.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    private String customer;
    private LocalDateTime dateTime;
    private String type;
    private Employee employee;
    private List<OrderItem> orderItems;

    public Order() {
    }

    @Column(name = "customer", nullable = false)
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Column(name = "date_time", nullable = false)
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Column(name = "type", nullable = false, columnDefinition = "ENUM('ForHere', 'ToGo') default 'ForHere'")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employee_id", nullable = false)
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @OneToMany(mappedBy = "order")
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
