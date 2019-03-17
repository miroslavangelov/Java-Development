package app.repository;

import app.domain.entities.Capital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CapitalRepository extends JpaRepository<Capital, String> {
    @Query(
        "SELECT c\n" +
        "FROM app.domain.entities.Capital AS c\n" +
        "ORDER BY c.name"
    )
    List<Capital> findAllCapitals();
}
