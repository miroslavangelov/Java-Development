package app.service;

import app.domain.entities.JobApplication;
import app.domain.models.service.JobApplicationServiceModel;
import app.repository.JobApplicationRepository;
import app.util.ModelMapper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class JobApplicationServiceImpl implements JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final ModelMapper modelMapper;

    @Inject
    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository, ModelMapper modelMapper) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public JobApplicationServiceModel findById(Long id) {
        JobApplication jobApplication = this.jobApplicationRepository.findById(id);

        return this.modelMapper.map(jobApplication, JobApplicationServiceModel.class);
    }

    @Override
    public void addJob(JobApplicationServiceModel jobApplicationServiceModel) {
        JobApplication jobApplication = this.modelMapper.map(jobApplicationServiceModel, JobApplication.class);

        this.jobApplicationRepository.save(jobApplication);
    }

    @Override
    public List<JobApplicationServiceModel> findAllJobApplications() {
        List<JobApplication> jobApplications = this.jobApplicationRepository.findAll();
        List<JobApplicationServiceModel> jobApplicationServiceModels = new ArrayList<>();

        jobApplications.forEach(jobApplication -> {
            JobApplicationServiceModel jobApplicationServiceModel = this.modelMapper.map(jobApplication, JobApplicationServiceModel.class);
            jobApplicationServiceModels.add(jobApplicationServiceModel);
        });

        return jobApplicationServiceModels;
    }

    @Override
    public void deleteById(Long id) {
        this.jobApplicationRepository.deleteById(id);
    }
}
