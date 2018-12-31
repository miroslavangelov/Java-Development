package app.service;

import app.domain.entities.AgeRestriction;
import app.domain.entities.Book;
import app.domain.entities.ReducedBook;

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

    List<Book> getAllBooksByReleaseDateBefore(LocalDate localDate);

    List<Book> getAllBooksByTitleContains(String st);

    int countBooksByTitleLongerThan(int length);

    List<String> totalBookCopies();

    List<ReducedBook> reduceBook(String title);

    List<Book> updateBookCopies(LocalDate date, int copies);

    String removeBooks(int copies);

    int numberOfBooksByAuthor(String fullName);
}
