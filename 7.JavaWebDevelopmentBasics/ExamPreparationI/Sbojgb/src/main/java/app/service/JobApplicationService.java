package app.service;

import app.domain.models.service.JobApplicationServiceModel;

import java.util.List;

public interface JobApplicationService {
    JobApplicationServiceModel findById(Long id);

    void addJob(JobApplicationServiceModel jobApplicationServiceModel);

    List<JobApplicationServiceModel> findAllJobApplications();

    void deleteById(Long id);
}
