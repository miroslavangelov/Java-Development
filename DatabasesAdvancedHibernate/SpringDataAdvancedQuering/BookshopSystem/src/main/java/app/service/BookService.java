package app.service;

import app.domain.entities.AgeRestriction;
import app.domain.entities.Book;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    List<String> getAllBooksTitlesByReleaseDateAfter();
    List<String> getAllAuthorsWithBooksByReleaseDateAfter();
    List<String> getAllBooksByAuthor();
    void seedBooks() throws IOException;
    List<Book> getAllBooksByAgeRestriction(AgeRestriction ageRestriction);
    List<Book> getAllBooksByGoldenTypeAndCopiesLessThan();
    List<Book> getAllBooksByPriceBetween();
    List<Book> getAllBooksNotReleased(int year);
}
