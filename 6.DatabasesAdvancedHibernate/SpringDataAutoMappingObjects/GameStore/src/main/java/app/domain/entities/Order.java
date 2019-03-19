package app.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    private User buyer;
    private List<Game> products;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "buyer_id")
    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @ManyToMany
    @JoinTable(name = "orders_games",
            joinColumns =
            @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "game_id", referencedColumnName = "id"))
    public List<Game> getProducts() {
        return products;
    }

    public void setProducts(List<Game> products) {
        this.products = products;
    }
}
