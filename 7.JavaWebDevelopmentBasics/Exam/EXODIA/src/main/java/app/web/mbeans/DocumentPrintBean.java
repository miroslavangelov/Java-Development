package app.web.mbeans;

import app.domain.models.service.DocumentServiceModel;
import app.service.DocumentService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class DocumentPrintBean {
    private DocumentService documentService;

    public DocumentPrintBean() {

    }

    @Inject
    public DocumentPrintBean(DocumentService documentService) {
        this.documentService = documentService;
    }

    public DocumentServiceModel getDocument(Long id){
        return this.documentService.findById(id);
    }

    public void print() throws IOException {
        String id = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()).get("id");
        Long documentId = Long.parseLong(id);

        this.documentService.deleteById(documentId);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
    }
}
