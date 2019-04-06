package app.repository;

import app.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query(
            "SELECT c\n" +
                    "FROM app.domain.entities.Category AS c\n" +
                    "ORDER BY c.name"
    )
    List<Category> findAllCategories();
}
