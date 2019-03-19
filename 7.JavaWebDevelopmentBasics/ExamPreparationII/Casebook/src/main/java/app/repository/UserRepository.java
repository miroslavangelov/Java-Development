package app.repository;

import app.domain.entities.User;

public interface UserRepository extends GenericRepository<User, Long> {
    User findByUsername(String username);

    void unfriendUser(Long id);
}
