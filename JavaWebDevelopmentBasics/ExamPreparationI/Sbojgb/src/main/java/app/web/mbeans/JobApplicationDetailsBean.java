package app.web.mbeans;

import app.domain.models.binding.JobApplicationAddBindingModel;
import app.domain.models.service.JobApplicationServiceModel;
import app.service.JobApplicationService;
import app.util.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class JobApplicationDetailsBean {
    private JobApplicationAddBindingModel jobApplicationAddBindingModel;
    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public JobApplicationDetailsBean() {

    }

    @Inject
    public JobApplicationDetailsBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
        this.initJob();
    }

    public JobApplicationAddBindingModel getJobApplicationAddBindingModel() {
        return jobApplicationAddBindingModel;
    }

    public void setJobApplicationAddBindingModel(JobApplicationAddBindingModel jobApplicationAddBindingModel) {
        this.jobApplicationAddBindingModel = jobApplicationAddBindingModel;
    }

    private void initJob() {
        Long jobId = Long.parseLong((FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()).get("id"));
        JobApplicationServiceModel jobApplicationServiceModel = this.jobApplicationService.findById(jobId);
        this.jobApplicationAddBindingModel = this.modelMapper.map(jobApplicationServiceModel, JobApplicationAddBindingModel.class);
    }
}
