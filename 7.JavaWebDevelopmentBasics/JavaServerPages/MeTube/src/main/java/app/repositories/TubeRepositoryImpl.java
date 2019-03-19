package app.repositories;

import app.domain.entities.Tube;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class TubeRepositoryImpl implements TubeRepository {
    private EntityManager entityManager;

    public TubeRepositoryImpl() {
        this.entityManager = Persistence.createEntityManagerFactory("me_tube").createEntityManager();
    }

    @Override
    public Tube findByName(String name) {
        this.entityManager.getTransaction().begin();
        Tube tube = this.entityManager.createQuery("SELECT t FROM tubes AS t WHERE t.title = :name", Tube.class)
                .setParameter("name", name)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return tube;
    }

    @Override
    public Tube save(Tube entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public Tube findById(String id) {
        this.entityManager.getTransaction().begin();
        Tube tube = this.entityManager.createQuery("SELECT t FROM tubes AS t WHERE t.id = :id", Tube.class)
                .setParameter("id", id)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return tube;
    }

    @Override
    public List<Tube> findAll() {
        this.entityManager.getTransaction().begin();
        List<Tube> tubes = this.entityManager.createQuery("SELECT t FROM tubes AS t", Tube.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return tubes;
    }
}
