package app.service;

import app.domain.models.service.DocumentServiceModel;

import java.util.List;

public interface DocumentService {
    DocumentServiceModel findById(String id);

    List<DocumentServiceModel> findAllDocuments();

    DocumentServiceModel scheduleDocument(DocumentServiceModel documentServiceModel);

    void deleteById(String id);
}
