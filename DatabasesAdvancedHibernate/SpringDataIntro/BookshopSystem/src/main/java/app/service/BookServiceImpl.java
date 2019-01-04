package app.service;

import app.domain.entities.*;
import app.repository.AuthorRepository;
import app.repository.BookRepository;
import app.repository.CategoryRepository;
import app.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final static String BOOKS_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\BookshopSystem\\src\\main\\resources\\files\\books.txt";
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    public List<String> getAllBooksTitlesByReleaseDateAfter() {
        List<Book> books = this.bookRepository.findAllByReleaseDateAfter(LocalDate.parse("2000-12-31"));

        return books.stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllAuthorsWithBooksByReleaseDateAfter() {
        List<Book> books = this.bookRepository.findAllByReleaseDateBefore(LocalDate.parse("1990-01-01"));

        return books.stream().map(
                book -> String.format(
                        "%s %s",
                        book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName())
        ).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBooksByAuthor() {
        List<Book> books = this.bookRepository.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc("George", "Powell");

        return books.stream().map(
                book -> String.format(
                        "%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies())
        ).collect(Collectors.toList());
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() != 0) {
            return;
        }

        String[] bookFileContent = this.fileUtil.getFileContent(BOOKS_FILE_PATH);
        for (String currentLine: bookFileContent) {
            String[] bookData = currentLine.split("\\s+");
            Book book = new Book();
            EditionType editionType = EditionType.values()[Integer.parseInt(bookData[0])];
            book.setEditionType(editionType);
            LocalDate releaseDate = LocalDate.parse(bookData[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
            book.setReleaseDate(releaseDate);
            Integer copies = Integer.parseInt(bookData[2]);
            book.setCopies(copies);
            BigDecimal price = new BigDecimal(bookData[3]);
            book.setPrice(price);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(bookData[4])];
            book.setAgeRestriction(ageRestriction);

            StringBuilder title = new StringBuilder();
            for (int i = 5; i < bookData.length; i++) {
                title.append(bookData[i]).append(" ");
            }
            book.setTitle(title.toString().trim());

            Author author = this.getRandomAuthor();
            book.setAuthor(author);
            Set<Category> categories = this.getRandomCategories();
            book.setCategories(categories);

            this.bookRepository.saveAndFlush(book);
        }
    }

    private Author getRandomAuthor() {
        Random random = new Random();
        int randomId = random.nextInt((int) (this.authorRepository.count() - 1)) + 1;

        return this.authorRepository.getOne(randomId);
    }

    private Category getRandomCategory() {
        Random random = new Random();
        int randomId = random.nextInt((int) (this.categoryRepository.count() - 1)) + 1;

        return this.categoryRepository.getOne(randomId);
    }

    private Set<Category> getRandomCategories() {
        Set<Category> categories = new LinkedHashSet<>();

        Random random = new Random();
        int length = random.nextInt(2);

        for (int i = 0; i < length; i++) {
            Category category = this.getRandomCategory();

            categories.add(category);
        }

        return categories;
    }
}
