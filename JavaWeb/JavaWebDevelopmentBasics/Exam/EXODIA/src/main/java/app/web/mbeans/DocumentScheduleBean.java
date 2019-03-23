package app.web.mbeans;

import app.domain.models.binding.DocumentScheduleBindingModel;
import app.domain.models.service.DocumentServiceModel;
import app.service.DocumentService;
import app.util.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class DocumentScheduleBean {
    private DocumentScheduleBindingModel documentScheduleBindingModel;
    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DocumentScheduleBean() {
        this.documentScheduleBindingModel = new DocumentScheduleBindingModel();
    }

    @Inject
    public DocumentScheduleBean(DocumentService documentService, ModelMapper modelMapper) {
        this();
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    public DocumentScheduleBindingModel getDocumentScheduleBindingModel() {
        return documentScheduleBindingModel;
    }

    public void setDocumentScheduleBindingModel(DocumentScheduleBindingModel documentScheduleBindingModel) {
        this.documentScheduleBindingModel = documentScheduleBindingModel;
    }

    public void scheduleDocument() throws IOException {
        this.documentService.scheduleDocument(this.modelMapper.map(this.documentScheduleBindingModel, DocumentServiceModel.class));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/details");
    }
}
