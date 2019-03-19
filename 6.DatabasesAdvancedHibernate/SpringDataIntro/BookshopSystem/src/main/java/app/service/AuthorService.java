package app.service;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    List<String> getAllAuthorsOrderedByBooksCount();
    void seedAuthors() throws IOException;
}
