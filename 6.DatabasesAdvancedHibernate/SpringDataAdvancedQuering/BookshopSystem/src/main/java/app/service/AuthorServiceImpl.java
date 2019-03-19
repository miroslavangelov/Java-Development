package app.service;

import app.domain.entities.Author;
import app.repository.AuthorRepository;
import app.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final static String AUTHORS_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\BookshopSystem\\src\\main\\resources\\files\\authors.txt";
    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public List<String> getAllAuthorsOrderedByBooksCount() {
        List<Author> authors = this.authorRepository.findAuthorsByOrOrderByBooksDesc();

        return authors.stream().map(
                author -> String.format(
                        "%s %s %d",
                        author.getFirstName(),
                        author.getLastName(),
                        author.getBooks().size())
        ).collect(Collectors.toList());
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() != 0) {
            return;
        }

        String[] authorFileContent = this.fileUtil.getFileContent(AUTHORS_FILE_PATH);
        for (String currentLine: authorFileContent) {
            String[] names = currentLine.split("\\s+");
            Author author = new Author();
            author.setFirstName(names[0]);
            author.setLastName(names[1]);

            this.authorRepository.saveAndFlush(author);
        }
    }

    @Override
    public List<Author> getAllAuthorsFirstNameEndsWith(String endsWith) {
        return this.authorRepository.findAllByFirstNameEndsWith(endsWith);
    }

    @Override
    public List<Author> getAllAuthorsLastnameStartsWith(String st) {
        return this.authorRepository.findAllByLastNameStartsWith(st);
    }
}
