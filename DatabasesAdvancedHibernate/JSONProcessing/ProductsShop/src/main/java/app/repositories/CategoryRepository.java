package app.repositories;

import app.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "SELECT c.name, COUNT(p.id) AS products_count, " +
            "AVG(p.price) AS average_price, SUM(p.price) AS total_revenue\n" +
            "FROM categories AS c\n" +
            "INNER JOIN categories_products AS cp ON cp.category_id = c.id\n" +
            "INNER JOIN products AS p ON p.id = cp.product_id\n" +
            "GROUP BY cp.category_id\n" +
            "ORDER BY products_count DESC;", nativeQuery = true)
    List<Object[]> getCategoriesByProductsCount();
}
