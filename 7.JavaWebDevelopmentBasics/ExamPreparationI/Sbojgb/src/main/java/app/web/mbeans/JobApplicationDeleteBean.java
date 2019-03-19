package app.web.mbeans;

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
public class JobApplicationDeleteBean {
    private JobApplicationService jobApplicationService;

    public JobApplicationDeleteBean() {

    }

    @Inject
    public JobApplicationDeleteBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
    }

    public JobApplicationServiceModel getJob(Long id){
        return this.jobApplicationService.findById(id);
    }

    public void deleteJob() throws IOException {
        String id = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()).get("id");
        Long jobId = Long.parseLong(id);
        this.jobApplicationService.deleteById(jobId);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
    }
}
