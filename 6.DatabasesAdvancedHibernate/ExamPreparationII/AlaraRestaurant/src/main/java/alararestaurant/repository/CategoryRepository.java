package alararestaurant.repository;

import alararestaurant.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);

    @Query(
            "SELECT c\n" +
            "FROM alararestaurant.domain.entities.Category AS c\n" +
            "INNER JOIN c.items AS i\n" +
            "GROUP BY c.id\n" +
            "ORDER BY SIZE(c.items) DESC, SUM(i.price) DESC"
    )
    List<Category> exportCategories();
}
