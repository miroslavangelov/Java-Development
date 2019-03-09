package app.service;

import app.domain.entities.Document;
import app.domain.models.service.DocumentServiceModel;
import app.repository.DocumentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }


    public DocumentServiceModel findById(String id) {
        Document document = this.documentRepository.findById(id).orElse(null);

        if (document == null){
            return null;
        }

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
    public DocumentServiceModel scheduleDocument(DocumentServiceModel documentServiceModel) {
        Document document = this.documentRepository.save(this.modelMapper.map(documentServiceModel, Document.class));

        return this.modelMapper.map(document, DocumentServiceModel.class);
    }

    @Override
    public void deleteById(String id) {
        this.documentRepository.deleteById(id);
    }
}
