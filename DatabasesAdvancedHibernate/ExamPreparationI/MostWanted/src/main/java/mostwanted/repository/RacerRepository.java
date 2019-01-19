package mostwanted.repository;

import mostwanted.domain.entities.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RacerRepository extends JpaRepository<Racer, Integer> {
    Racer findByName(String name);

    @Query(
            "SELECT r\n" +
            "FROM mostwanted.domain.entities.Racer AS r\n" +
            "JOIN r.cars AS c\n" +
            "GROUP BY r.id\n" +
            "ORDER BY SIZE(r.cars) DESC, r.name"
    )
    List<Racer> exportRacers();
}
