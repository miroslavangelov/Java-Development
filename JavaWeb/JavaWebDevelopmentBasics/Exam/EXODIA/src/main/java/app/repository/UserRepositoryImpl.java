package app.repository;

import app.domain.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;

    public UserRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("exodia")
                .createEntityManager();
    }

    @Override
    public User save(User entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public User findById(Long id) {
        this.entityManager.getTransaction().begin();
        try {
            User user = this.entityManager.createQuery("SELECT u FROM User AS u WHERE u.id = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
            this.entityManager.getTransaction().commit();

            return user;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public User findByUsername(String username) {
        this.entityManager.getTransaction().begin();
        try {
            User user = this.entityManager.createQuery("SELECT u FROM User AS u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            this.entityManager.getTransaction().commit();
            return user;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }
}
