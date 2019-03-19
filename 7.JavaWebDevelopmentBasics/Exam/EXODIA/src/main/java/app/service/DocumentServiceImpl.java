package app.service;

import app.domain.entities.Document;
import app.domain.models.service.DocumentServiceModel;
import app.repository.DocumentRepository;
import app.util.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;

    @Inject
    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DocumentServiceModel findById(Long id) {
        Document document = this.documentRepository.findById(id);

        return this.modelMapper.map(document, DocumentServiceModel.class);
    }

    @Override
    public List<DocumentServiceModel> findAllDocuments() {
        return this.documentRepository.findAll()
                .stream()
                .map(document -> this.modelMapper.map(document, DocumentServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void scheduleDocument(DocumentServiceModel documentServiceModel) {
        Document document = this.modelMapper.map(documentServiceModel, Document.class);

        this.documentRepository.save(document);
    }

    @Override
    public void deleteById(Long id) {
        this.documentRepository.deleteById(id);
    }
}
