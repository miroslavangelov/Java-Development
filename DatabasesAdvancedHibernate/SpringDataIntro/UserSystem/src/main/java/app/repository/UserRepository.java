package app.repository;

import app.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByEmailEndsWith(String provider);

    List<User> findAllByLastTimeLoggedInLessThan(LocalDateTime date);

    List<User> deleteAllByDeleted(Boolean isDeleted);
}
