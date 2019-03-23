DELIMITER //
CREATE PROCEDURE totalNumberOfBooksByAuthor(IN fullName varchar(50), OUT booksCount INT)
  BEGIN
    SET booksCount = (SELECT COUNT(b.id)
        FROM authors AS a INNER JOIN books AS b ON b.author_id = a.id
        WHERE CONCAT(a.first_name, ' ', a.last_name) = fullName);
    SELECT booksCount;
  END //
DELIMITER ;