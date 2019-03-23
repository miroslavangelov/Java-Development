package mostwanted.repository;

import mostwanted.domain.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {
    Town findByName(String name);

    @Query(value = "SELECT t.name, COUNT(r.id) AS racers_count\n" +
            "FROM towns AS t\n" +
            "INNER JOIN racers AS r ON r.town_id = t.id\n" +
            "GROUP BY t.id\n" +
            "ORDER BY racers_count DESC, t.name;", nativeQuery = true)
    List<Object[]> exportTowns();
}
