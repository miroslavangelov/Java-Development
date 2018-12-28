package app.controller;

import app.domain.entities.AgeRestriction;
import app.domain.entities.Book;
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
//                "%s %s",
//                book.getTitle(),
//                book.getPrice().toString()
//        )));

//        Problem 4
        int year = Integer.parseInt(reader.readLine());
        List<Book> booksByNotReleasedYear = this.bookService.getAllBooksNotReleased(year);
        booksByNotReleasedYear.forEach(book -> System.out.println(book.getTitle()));
    }
}
