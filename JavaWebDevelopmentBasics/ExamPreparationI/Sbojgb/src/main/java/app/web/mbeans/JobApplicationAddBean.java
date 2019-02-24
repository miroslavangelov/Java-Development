package app.web.mbeans;

import app.domain.models.binding.JobApplicationAddBindingModel;
import app.domain.models.service.JobApplicationServiceModel;
import app.service.JobApplicationService;
import app.util.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class JobApplicationAddBean {
    private JobApplicationAddBindingModel jobApplicationAddBindingModel;
    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public JobApplicationAddBean() {
        this.jobApplicationAddBindingModel = new JobApplicationAddBindingModel();
    }

    @Inject
    public JobApplicationAddBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this();
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    public JobApplicationAddBindingModel getJobApplicationAddBindingModel() {
        return jobApplicationAddBindingModel;
    }

    public void setJobApplicationAddBindingModel(JobApplicationAddBindingModel jobApplicationAddBindingModel) {
        this.jobApplicationAddBindingModel = jobApplicationAddBindingModel;
    }

    public void addJob() throws IOException {
        this.jobApplicationService.addJob(this.modelMapper.map(this.jobApplicationAddBindingModel, JobApplicationServiceModel.class));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
    }
}
