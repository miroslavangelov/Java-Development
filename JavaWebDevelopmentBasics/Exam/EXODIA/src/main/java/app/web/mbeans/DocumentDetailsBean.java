package app.web.mbeans;

import app.domain.models.service.DocumentServiceModel;
import app.service.DocumentService;
import app.util.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class DocumentDetailsBean {
    private DocumentServiceModel documentServiceModel;
    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DocumentDetailsBean() {

    }

    @Inject
    public DocumentDetailsBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
        this.initDocument();
    }

    public DocumentServiceModel getDocumentServiceModel() {
        return documentServiceModel;
    }

    public void setDocumentServiceModel(DocumentServiceModel documentServiceModel) {
        this.documentServiceModel = documentServiceModel;
    }

    private void initDocument() {
        Long documentId = Long.parseLong((FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()).get("id"));
        DocumentServiceModel documentServiceModel = this.documentService.findById(documentId);
        this.documentServiceModel = this.modelMapper.map(documentServiceModel, DocumentServiceModel.class);
    }
}
