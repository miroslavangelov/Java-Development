package app.repository;

import app.domain.entities.Document;

import java.util.List;

public interface DocumentRepository extends GenericRepository<Document, Long> {
    List<Document> findAll();

    void deleteById(Long id);
}
