package app.service;

import java.io.IOException;
import java.util.List;

public interface BookService {
    List<String> getAllBooksTitlesByReleaseDateAfter();
    List<String> getAllAuthorsWithBooksByReleaseDateAfter();
    List<String> getAllBooksByAuthor();
    void seedBooks() throws IOException;
}
