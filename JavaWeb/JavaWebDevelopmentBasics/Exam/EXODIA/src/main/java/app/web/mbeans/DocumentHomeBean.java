package app.web.mbeans;

import app.domain.models.view.DocumentHomeViewModel;
import app.service.DocumentService;
import app.util.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class DocumentHomeBean {
    private List<DocumentHomeViewModel> documents;
    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DocumentHomeBean() {

    }

    @Inject
    public DocumentHomeBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
        this.initDocuments();
    }

    public List<DocumentHomeViewModel> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentHomeViewModel> documents) {
        this.documents = documents;
    }

    private void initDocuments() {
        this.documents = this.documentService.findAllDocuments().stream()
                .map(document -> {
                    DocumentHomeViewModel model = this.modelMapper.map(document, DocumentHomeViewModel.class);
                    if (model.getTitle().length() >= 12) {
                        model.setTitle(model.getTitle().substring(0, 12) + "...");
                    }

                    return model;
                })
                .collect(Collectors.toList());
    }
}
