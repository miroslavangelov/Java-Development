package app.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private int age;
    private List<User> friends;
    private List<Product> boughtProducts;
    private List<Product> soldProducts;

    public User() {
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @ManyToMany
    @JoinTable(name = "users_friends",
            joinColumns =
            @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    @OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER)
    public List<Product> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(List<Product> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER)
    public List<Product> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<Product> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
