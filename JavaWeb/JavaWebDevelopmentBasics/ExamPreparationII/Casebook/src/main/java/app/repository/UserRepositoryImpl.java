package app.repository;

import app.domain.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;

    public UserRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("casebook")
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
    public User update(User entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public List<User> findAll() {
        this.entityManager.getTransaction().begin();
        List<User> users = this.entityManager.createQuery("SELECT u FROM users AS u", User.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return users;
    }

    @Override
    public User findById(Long id) {
        this.entityManager.getTransaction().begin();
        try {
            User user = this.entityManager.createQuery("SELECT u FROM users AS u WHERE u.id = :id", User.class)
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
            User user = this.entityManager.createQuery("SELECT u FROM users AS u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            this.entityManager.getTransaction().commit();
            return user;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public void unfriendUser(Long id) {
        this.entityManager.getTransaction().begin();
        try {
            this.entityManager.createNativeQuery("DELETE FROM users_friends WHERE friend_id = " + id)
                    .executeUpdate();
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        }
    }
}
