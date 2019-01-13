package app.repositories;

import app.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT u.* FROM users AS u\n" +
            "INNER JOIN products AS p ON p.seller_id = u.id\n" +
            "WHERE p.buyer_id IS NOT NULL\n" +
            "GROUP BY u.id\n" +
            "ORDER BY u.last_name, u.first_name;", nativeQuery = true)
    List<User> findUsersBySoldProducts();
}
