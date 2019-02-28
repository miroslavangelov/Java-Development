package app.service;

import app.domain.models.service.DocumentServiceModel;

import java.util.List;

public interface DocumentService {
    DocumentServiceModel findById(Long id);

    List<DocumentServiceModel> findAllDocuments();

    void scheduleDocument(DocumentServiceModel documentServiceModel);

    void deleteById(Long id);
}
