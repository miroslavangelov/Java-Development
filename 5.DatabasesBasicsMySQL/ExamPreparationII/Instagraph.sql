--01. Table Design
CREATE TABLE pictures (
	id INT PRIMARY KEY AUTO_INCREMENT,
    path VARCHAR(255) NOT NULL,
    size DECIMAL(10, 2) NOT NULL
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE users (
	id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
	profile_picture_id INT,
	CONSTRAINT `fk_users_profile_picture_id` FOREIGN KEY (profile_picture_id) REFERENCES pictures(id)
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE posts (
	id INT PRIMARY KEY AUTO_INCREMENT,
    caption VARCHAR(255) NOT NULL,
    user_id INT NOT NULL,
	picture_id INT NOT NULL,
	CONSTRAINT `fk_posts_user_id` FOREIGN KEY (user_id) REFERENCES users(id),
	CONSTRAINT `fk_posts_picture_id` FOREIGN KEY (picture_id) REFERENCES pictures(id)
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE comments (
	id INT PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(255) NOT NULL,
    user_id INT NOT NULL,
	post_id INT NOT NULL,
	CONSTRAINT `fk_comments_user_id` FOREIGN KEY (user_id) REFERENCES users(id),
	CONSTRAINT `fk_comments_post_id` FOREIGN KEY (post_id) REFERENCES posts(id)
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE users_followers (
    user_id INT,
	follower_id INT,
	CONSTRAINT `fk_users_followers_user_id` FOREIGN KEY (user_id) REFERENCES users(id),
	CONSTRAINT `fk_users_followers_follower_id` FOREIGN KEY (follower_id) REFERENCES users(id)
) ENGINE=InnoDB COLLATE=utf8_general_ci;

--02. Insert
INSERT INTO comments(content, user_id, post_id)
SELECT CONCAT('Omg!', users.username, '!This is so cool!'), CEIL((posts.id * 3) / 2), posts.id
FROM posts
INNER JOIN users ON users.id = posts.user_id
WHERE posts.id BETWEEN 1 AND 10;

--03. Update
UPDATE users AS u1
INNER JOIN (SELECT COUNT(uf.follower_id) AS followers_count
	FROM users AS u2
	INNER JOIN users_followers AS uf ON uf.user_id = u2.id
	GROUP BY u2.id
) AS fc
SET u1.profile_picture_id = (
	CASE WHEN fc.followers_count > 0
	THEN fc.followers_count
	ELSE u1.id
	END
)
WHERE u1.profile_picture_id IS NULL;

--04. Delete
DELETE users FROM users
LEFT JOIN users_followers AS uf1 ON uf1.user_id = users.id
LEFT JOIN users_followers AS uf2 ON uf2.follower_id = users.id
WHERE uf1.user_id IS NULL
AND uf2.follower_id IS NULL;

--05. Users
SELECT id, username
FROM users
ORDER BY id;

--06. Cheaters
SELECT users.id, users.username
FROM users
INNER JOIN users_followers AS uf ON users.id = uf.user_id
WHERE uf.user_id = uf.follower_id
ORDER BY users.id;

--07. High Quality Pictures
SELECT id, path, size
FROM pictures
WHERE size > 50000
AND (path LIKE '%jpeg' OR path LIKE '%png')
ORDER BY size DESC;

--08. Comments and Users
SELECT comments.id, CONCAT(users.username, ' : ', comments.content) AS full_comment
FROM comments
INNER JOIN users ON users.id = comments.user_id
ORDER BY comments.id DESC;

--09. Profile Pictures
SELECT u1.id, u1.username, CONCAT(pictures.size, 'KB')
FROM users AS u1
INNER JOIN pictures ON pictures.ID = u1.profile_picture_id
INNER JOIN users AS u2 ON u1.profile_picture_id = u2.profile_picture_id
WHERE u1.id != u2.id
GROUP BY u1.id
ORDER BY u1.id;

--10. Spam Posts
SELECT p.id, p.caption, COUNT(c.id) AS `comments`
FROM posts AS p
LEFT JOIN comments AS c ON p.id = c.post_id
GROUP BY p.id
ORDER BY `comments` DESC, p.id
LIMIT 5;

--11. Most Popular User
SELECT u.id, u.username, COUNT(p.id) AS `posts`, uf.followers
FROM (
	SELECT uf.user_id AS user_id, COUNT(uf.follower_id) AS followers
	FROM users_followers AS uf
	GROUP BY uf.user_id
) AS uf
INNER JOIN users AS u ON uf.user_id = u.id
INNER JOIN posts AS p ON p.user_id = u.id
GROUP BY u.id
ORDER BY uf.followers DESC
LIMIT 1;

--12. Commenting Myself
SELECT u.id, u.username, COUNT(c.id) AS my_comments
FROM users AS u
LEFT JOIN posts AS p ON p.user_id = u.id
LEFT JOIN comments AS c ON c.user_id = u.id AND p.id = c.post_id
GROUP BY u.id
ORDER BY my_comments DESC, u.id;

--13. User Top Posts
SELECT u.id, u.username, p.caption
FROM users AS u
INNER JOIN (
	SELECT p1.id, p1.user_id, p1.caption, MAX(p1.comments_count)
	FROM (SELECT p2.id, COUNT(c.id) AS comments_count, p2.caption, p2.user_id
		FROM posts AS p2
		LEFT JOIN comments AS c ON p2.id = c.post_id
		GROUP BY p2.id
		ORDER BY comments_count DESC, p2.id) AS p1
	GROUP BY p1.user_id
	ORDER BY p1.user_id
) AS p ON u.id = p.user_id;

--14. Posts and Commentators
SELECT p.id, p.caption, COUNT(DISTINCT c.user_id) AS `users`
FROM posts AS p
LEFT JOIN comments AS c ON c.post_id = p.id
GROUP BY p.id
ORDER BY `users` DESC, p.id;

--15. Post
CREATE PROCEDURE udp_commit(username VARCHAR(30), password VARCHAR(30), caption VARCHAR(255), path VARCHAR(255))
BEGIN
	START TRANSACTION;
	IF (SELECT users.password FROM users WHERE users.username = username) != password
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Password is incorrect!';
		ROLLBACK;
	ELSEIF (SELECT pictures.id FROM pictures WHERE pictures.path = path) IS NULL
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'The picture does not exist!';
		ROLLBACK;
	ELSE
		INSERT INTO posts(caption, user_id, picture_id)
		VALUES (
			caption,
			(SELECT users.id FROM users WHERE users.username = username),
			(SELECT pictures.id FROM pictures WHERE pictures.path = path)
		);
		COMMIT;
	END IF;
END

--16. Filter
CREATE PROCEDURE udp_filter(hashtag VARCHAR(255))
BEGIN
	SELECT posts.id, posts.caption, users.username AS user
	FROM posts
	INNER JOIN users ON users.id = posts.user_id
	WHERE posts.caption LIKE concat('%', hashtag, '%')
	ORDER BY posts.id;
END