package app.service;

import app.domain.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    List<String> getAllAuthorsOrderedByBooksCount();

    void seedAuthors() throws IOException;

    List<Author> getAllAuthorsFirstNameEndsWith(String endsWith);

    List<Author> getAllAuthorsLastnameStartsWith(String st);
}
