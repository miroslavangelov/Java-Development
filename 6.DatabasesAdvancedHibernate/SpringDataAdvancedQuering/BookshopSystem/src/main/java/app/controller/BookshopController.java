package app.controller;

import app.domain.entities.AgeRestriction;
import app.domain.entities.Author;
import app.domain.entities.Book;
import app.domain.entities.EditionType;
import app.service.AuthorService;
import app.service.BookService;
import app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class BookshopController implements CommandLineRunner {
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    @Autowired
    public BookshopController(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        this.authorService.seedAuthors();
        this.categoryService.seedCategories();
        this.bookService.seedBooks();

//        Problem 1
//        AgeRestriction ageRestriction = AgeRestriction.valueOf(reader.readLine().toUpperCase());
//        List<Book> booksByAgeRestriction = this.bookService.getAllBooksByAgeRestriction(ageRestriction);
//        booksByAgeRestriction.forEach(book -> System.out.println(book.getTitle()));

//        Problem 2
//        List<Book> goldenBooksWithCopiesLessThan = this.bookService.getAllBooksByGoldenTypeAndCopiesLessThan();
//        goldenBooksWithCopiesLessThan.forEach(book -> System.out.println(book.getTitle()));

//        Problem 3
//        List<Book> booksWithPriceBetween = this.bookService.getAllBooksByPriceBetween();
//        booksWithPriceBetween.forEach(book -> System.out.println(String.format(
//                "%s %.2f",
//                book.getTitle(),
//                book.getPrice()
//        )));

//        Problem 4
//        int year = Integer.parseInt(reader.readLine());
//        List<Book> booksByNotReleasedYear = this.bookService.getAllBooksNotReleased(year);
//        booksByNotReleasedYear.forEach(book -> System.out.println(book.getTitle()));

//        Problem 5
//        String inputDate = reader.readLine();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        LocalDate date = LocalDate.parse(inputDate, formatter);
//        List<Book> booksByReleaseDateBefore = this.bookService.getAllBooksByReleaseDateBefore(date);
//        booksByReleaseDateBefore.forEach(book -> System.out.println(String.format(
//                "%s %s %.2f",
//                book.getTitle(),
//                book.getEditionType().name(),
//                book.getPrice()
//        )));

//        Problem 6
//        String endsWith = reader.readLine();
//        List<Author> authorsFirstNameEndsWith = this.authorService.getAllAuthorsFirstNameEndsWith(endsWith);
//        authorsFirstNameEndsWith.forEach(
//                author -> System.out.println(String.format("%s %s", author.getFirstName(), author.getLastName()))
//        );

//        Problem 7
//        String st = reader.readLine().toLowerCase();
//        List<Book> booksTitleContains = this.bookService.getAllBooksByTitleContains(st);
//        booksTitleContains.forEach(book -> System.out.println(book.getTitle()));

//        Problem 8
//        String st = reader.readLine();
//        List<Author> authorsLastNameEndsWith = this.authorService.getAllAuthorsLastnameStartsWith(st);
//        authorsLastNameEndsWith.forEach(
//                author -> author.getBooks().forEach(
//                        book -> System.out.println(String.format(
//                                "%s (%s %s)",
//                                book.getTitle(),
//                                author.getFirstName(),
//                                author.getLastName()
//                        ))
//                )
//        );

//        Problem 9
//        int length = Integer.parseInt(reader.readLine());
//        System.out.println(this.bookService.countBooksByTitleLongerThan(length));

//        Problem 10
//        this.bookService.totalBookCopies().forEach(System.out::println);

//        Problem 11
//        String title = reader.readLine();
//        this.bookService.reduceBook(title)
//                .forEach(reducedBook -> System.out.println(String.format(
//                        "%s %s %s %s",
//                        reducedBook.getTitle(),
//                        reducedBook.getEditionType(),
//                        reducedBook.getAgeRestriction(),
//                        reducedBook.getPrice()
//                )));

//        Problem 12
//        String inputDate = reader.readLine();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
//        LocalDate date = LocalDate.parse(inputDate, formatter);
//        int copies = Integer.parseInt(reader.readLine());
//        List<Book> booksAfterDate = this.bookService.updateBookCopies(date, copies);
//        System.out.println(booksAfterDate.size() * copies);

//        Problem 13
//        Integer copies = Integer.parseInt(reader.readLine());
//        System.out.println(this.bookService.removeBooks(copies));

//        Problem 14
        String authorFullName = reader.readLine();
        int booksCount = this.bookService.numberOfBooksByAuthor(authorFullName);
        if (booksCount == 0) {
            System.out.println(String.format("%s has not written any books yet", authorFullName));
        } else {
            System.out.println(String.format(
                    "%s has written %d %s",
                    authorFullName,
                    booksCount,
                    booksCount > 1 ? "books" : "book"
            ));
        }
    }
}
