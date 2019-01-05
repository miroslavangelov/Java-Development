package app.repositories;

import app.domain.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    Game findById(Long id);

    @Transactional
    void deleteById(Long id);

    Game findByTitle(String title);
}
