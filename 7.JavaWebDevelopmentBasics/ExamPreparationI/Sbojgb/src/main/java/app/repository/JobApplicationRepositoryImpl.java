package app.repository;

import app.domain.entities.JobApplication;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class JobApplicationRepositoryImpl implements JobApplicationRepository {
    private final EntityManager entityManager;

    public JobApplicationRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("spojgb")
                .createEntityManager();
    }

    @Override
    public JobApplication save(JobApplication entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public JobApplication findById(Long id) {
        this.entityManager.getTransaction().begin();
        try {
            JobApplication jobApplication = this.entityManager.createQuery("SELECT ja FROM job_applications AS ja WHERE ja.id = :id", JobApplication.class)
                    .setParameter("id", id)
                    .getSingleResult();
            this.entityManager.getTransaction().commit();

            return jobApplication;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public List<JobApplication> findAll() {
        this.entityManager.getTransaction().begin();
        List<JobApplication> jobApplications = this.entityManager.createQuery("SELECT ja FROM job_applications AS ja", JobApplication.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return jobApplications;
    }

    @Override
    public void deleteById(Long id) {
        this.entityManager.getTransaction().begin();
        try {
            this.entityManager.createNativeQuery("DELETE FROM job_applications WHERE id = " + id)
                    .executeUpdate();
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        }
    }
}
