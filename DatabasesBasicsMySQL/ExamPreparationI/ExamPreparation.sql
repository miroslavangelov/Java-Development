--01. Table Design
--CREATE DATABASE buhtig;
CREATE TABLE users (
	id INT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(30) UNIQUE NOT NULL,
	password VARCHAR(30) NOT NULL,
	email VARCHAR(50) NOT NULL
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE repositories (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE repositories_contributors (
	repository_id INT,
	contributor_id INT,
	CONSTRAINT `fk_repositories_contributors_repository_id` FOREIGN KEY (repository_id) REFERENCES repositories(id),
	CONSTRAINT `fk_repositories_contributors_contributor_id` FOREIGN KEY (contributor_id) REFERENCES users(id)
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE issues (
	id INT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(255) NOT NULL,
	issue_status VARCHAR(6) NOT NULL,
	repository_id INT NOT NULL,
	assignee_id INT NOT NULL,
	CONSTRAINT `fk_issues_repository_id` FOREIGN KEY (repository_id) REFERENCES repositories(id),
	CONSTRAINT `fk_issues_assignee_id` FOREIGN KEY (assignee_id) REFERENCES users(id)
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE commits (
	id INT PRIMARY KEY AUTO_INCREMENT,
	message VARCHAR(255) NOT NULL,
	issue_id INT,
	repository_id INT NOT NULL,
	contributor_id INT NOT NULL,
	CONSTRAINT `fk_commits_issue_id` FOREIGN KEY (issue_id) REFERENCES issues(id),
	CONSTRAINT `fk_commits_repository_id` FOREIGN KEY (repository_id) REFERENCES repositories(id),
	CONSTRAINT `fk_commits_contributor_id` FOREIGN KEY (contributor_id) REFERENCES users(id)
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE files (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	size DECIMAL(10,2) NOT NULL,
	parent_id INT,
	commit_id INT NOT NULL,
	CONSTRAINT `fk_files_parent_id` FOREIGN KEY (parent_id) REFERENCES files(id),
	CONSTRAINT `fk_files_commit_id` FOREIGN KEY (commit_id) REFERENCES commits(id)
) ENGINE=InnoDB COLLATE=utf8_general_ci;

--02. Insert
INSERT INTO issues(title, issue_status, repository_id, assignee_id)
SELECT CONCAT('Critical Problem With ', f.name, '!'), 'open', CEILING((f.id * 2) / 3), commits.contributor_id
FROM files AS f
INNER JOIN commits ON f.commit_id = commits.id
WHERE f.id IN (46, 47, 48, 49, 50);

--03. Update
UPDATE repositories_contributors AS rc
INNER JOIN (
	SELECT min(r.id) AS rid
    FROM repositories AS r
	LEFT JOIN repositories_contributors as rc2 on rc2.repository_id = r.id
	WHERE rc2.contributor_id IS NULL
) AS d 
SET rc.repository_id = d.rid
WHERE rc.contributor_id = rc.repository_id;

--04. Delete
DELETE repositories FROM repositories
LEFT JOIN issues ON repositories.id = issues.repository_id
WHERE issues.id IS NULL;

--05. Users
SELECT id, username
FROM users
ORDER BY id;

--06. Lucky Numbers
SELECT repository_id, contributor_id
FROM repositories_contributors
WHERE repository_id = contributor_id
ORDER BY repository_id;

--07. Heavy HTML
SELECT id, name, size
FROM files
WHERE size > 1000
AND name LIKE '%html%'
ORDER BY size DESC;

--08. Issues And Users
SELECT issues.id, CONCAT(users.username, ' : ', issues.title) AS issue_assignee
FROM issues
INNER JOIN users ON issues.assignee_id = users.id
ORDER BY issues.id DESC;

--09. Non-Directory Files
SELECT f1.id, f1.name, CONCAT(f1.size, 'KB') AS size
FROM files AS f1
LEFT JOIN files AS f2 ON f1.id = f2.parent_id
WHERE f2.parent_id IS NULL
ORDER BY f1.id;

--10. Active Repositories
SELECT repositories.id, repositories.name, COUNT(issues.id) AS `issues`
FROM issues
INNER JOIN repositories ON issues.repository_id = repositories.id
GROUP BY issues.repository_id
ORDER BY `issues` DESC, repositories.id
LIMIT 5;

--11. Most Contributed Repository
SELECT r.id, r.name, COUNT(c.id) AS `commits`, rc.contributors
FROM (
	SELECT rc1.repository_id AS repository_id, COUNT(rc1.contributor_id) AS contributors
	FROM repositories_contributors AS rc1
	GROUP BY rc1.repository_id
) AS rc
INNER JOIN repositories AS r ON r.id = rc.repository_id
INNER JOIN commits AS c ON rc.repository_id = c.repository_id
GROUP BY rc.repository_id
ORDER BY rc.contributors DESC, rc.repository_id
LIMIT 1;

--12. Fixing My Own Problems
SELECT users.id, users.username, COUNT(commits.id) AS `commits`
FROM users
LEFT JOIN issues ON users.id = issues.assignee_id
LEFT JOIN commits ON commits.issue_id = issues.id AND users.id = commits.contributor_id
GROUP BY users.id
ORDER BY `commits` DESC, users.id;

--13. Recursive Commits 
SELECT SUBSTRING_INDEX(f1.name, '.', 1) AS `file`,
(SELECT COUNT(c.id)
	FROM commits AS c
	WHERE c.message LIKE CONCAT('%', f1.name, '%')) AS recursive_count
FROM files AS f1
INNER JOIN files AS f2 ON f1.id = f2.parent_id
WHERE f2.id = f1.parent_id
AND  f1.id != f2.id;

--14. Repositories And Commits
SELECT r.id, r.name, COUNT(DISTINCT c.contributor_id) AS `users`
FROM repositories AS r
LEFT JOIN commits AS c ON c.repository_id = r.id
GROUP BY r.id
ORDER BY `users` DESC, r.id;

--15. Commit
CREATE PROCEDURE udp_commit(username VARCHAR(30), password VARCHAR(30), message VARCHAR(255), issue_id INT)
BEGIN
	START TRANSACTION;
	IF (SELECT users.id FROM users WHERE users.username = username) IS NULL
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No such user!';
		ROLLBACK;
	ELSEIF (SELECT users.password FROM users WHERE users.username = username) != password
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Password is incorrect!';
		ROLLBACK;
	ELSEIF (SELECT issues.id FROM issues WHERE issues.id = issue_id) IS NULL
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'The issue does not exist!';
		ROLLBACK;
	ELSE
		INSERT INTO commits(message, issue_id, repository_id, contributor_id)
		VALUES (
		message,
		issue_id,
		(SELECT issues.repository_id FROM issues WHERE issues.id = issue_id),
		(SELECT users.id FROM users WHERE users.username = username));
		UPDATE issues
		SET issue_status = 'closed'
		WHERE issues.id = issue_id;
		COMMIT;
	END IF;
END

--16. Filter Extensions
CREATE PROCEDURE udp_findbyextension (extension VARCHAR(100))
BEGIN
	SELECT id, name, CONCAT(size, 'KB') AS size
	FROM files
	WHERE name LIKE concat('%', extension)
	ORDER BY id;
END