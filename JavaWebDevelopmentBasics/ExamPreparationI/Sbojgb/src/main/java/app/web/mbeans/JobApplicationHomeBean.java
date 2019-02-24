package app.web.mbeans;

import app.domain.models.view.JobApplicationHomeViewModel;
import app.service.JobApplicationService;
import app.util.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class JobApplicationHomeBean {
    private List<JobApplicationHomeViewModel> jobs;
    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public JobApplicationHomeBean(){
    }

    @Inject
    public JobApplicationHomeBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
        this.initJobs();
    }

    private void initJobs() {
        this.jobs = this.jobApplicationService.findAllJobApplications().stream()
                .map(jobApplication -> this.modelMapper.map(jobApplication, JobApplicationHomeViewModel.class))
                .collect(Collectors.toList());
    }

    public List<JobApplicationHomeViewModel> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobApplicationHomeViewModel> jobs) {
        this.jobs = jobs;
    }

    public void deleteJob(Long id) throws IOException {
        this.jobApplicationService.deleteById(id);

        FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
    }
}
