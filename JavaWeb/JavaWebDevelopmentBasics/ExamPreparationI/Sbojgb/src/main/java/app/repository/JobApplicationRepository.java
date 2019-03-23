package app.repository;

import app.domain.entities.JobApplication;

import java.util.List;

public interface JobApplicationRepository extends GenericRepository<JobApplication, Long> {
    List<JobApplication> findAll();

    void deleteById(Long id);
}
