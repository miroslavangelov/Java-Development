package app.repository;

import app.domain.entities.Document;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class DocumentRepositoryImpl implements DocumentRepository {
    private final EntityManager entityManager;

    public DocumentRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("exodia")
                .createEntityManager();
    }

    @Override
    public Document save(Document entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public Document findById(Long id) {
        this.entityManager.getTransaction().begin();
        try {
            Document document = this.entityManager.createQuery("SELECT d FROM Document AS d WHERE d.id = :id", Document.class)
                    .setParameter("id", id)
                    .getSingleResult();
            this.entityManager.getTransaction().commit();

            return document;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public List<Document> findAll() {
        this.entityManager.getTransaction().begin();
        List<Document> documents = this.entityManager.createQuery("SELECT d FROM Document AS d", Document.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return documents;
    }

    @Override
    public void deleteById(Long id) {
        this.entityManager.getTransaction().begin();
        this.entityManager
                .createNativeQuery("DELETE FROM exodia.documents WHERE id=" + id)
                .executeUpdate();
        this.entityManager.getTransaction().commit();
    }
}
