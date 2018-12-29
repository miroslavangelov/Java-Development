package app.repository;

import app.domain.entities.AgeRestriction;
import app.domain.entities.Book;
import app.domain.entities.EditionType;
import app.domain.entities.ReducedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByReleaseDateAfter(LocalDate localDate);

    List<Book> findAllByReleaseDateBefore(LocalDate localDate);

    List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findAllByPriceBetween(BigDecimal startIndex, BigDecimal endIndex);

    @Query(value = "SELECT * FROM books AS b WHERE year(b.release_date) != :year", nativeQuery = true)
    List<Book> findAllByReleaseDateYearIsNotIn(@Param("year") int year);

    List<Book> findAllByTitleContains(String st);

    @Query(value = "SELECT COUNT(b.id) FROM books AS b WHERE CHAR_LENGTH(b.title) > :length", nativeQuery = true)
    int countBooksByTitleGreaterThan(@Param("length") int length);

    @Query(value = "SELECT CONCAT(a.first_name, ' ', a.last_name, ' - ', SUM(b.copies)) " +
            "FROM books as b " +
            "INNER JOIN authors AS a ON a.id = b.author_id " +
            "GROUP BY a.id " +
            "ORDER BY SUM(b.copies) DESC", nativeQuery = true)
    List<String> totalBookCopies();

    @Query(value = "SELECT b.title, b.edition_type, b.age_restriction, b.price " +
            " FROM books AS b WHERE b.title = :title", nativeQuery = true)
    List<Object[]> reduceBook(@Param("title") String title);

    @Transactional
    List<Book> deleteAllByCopiesLessThan(int copies);
}
