package alararestaurant.repository;

import alararestaurant.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(
            "SELECT o\n" +
            "FROM alararestaurant.domain.entities.Order AS o\n" +
            "INNER JOIN o.employee AS e\n" +
            "INNER JOIN e.position AS p\n" +
            "WHERE p.name = :positionName\n" +
            "ORDER BY e.name, o.id"
    )
    List<Order> exportOrders(@Param(value = "positionName") String positionName);
}
