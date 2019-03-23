--01. One-To-One Relationship
CREATE TABLE passports (
	passport_id INT(10) UNSIGNED NOT NULL PRIMARY KEY,
	passport_number VARCHAR(50) UNIQUE NOT NULL
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE persons (
	person_id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(50) NOT NULL,
	salary DECIMAL(10,2) NOT NULL,
	passport_id INT(10) UNSIGNED UNIQUE NOT NULL,
	CONSTRAINT `fk_persons_pasports` FOREIGN KEY (`passport_id`) REFERENCES `passports` (`passport_id`)
) ENGINE=InnoDB COLLATE=utf8_general_ci;
INSERT INTO `passports`(`passport_id`, `passport_number`)
VALUES(101, 'N34FG21B'), (102, 'K65LO4R7'), (103, 'ZE657QP2');
INSERT INTO `persons`(`first_name`, `salary`, `passport_id`)
VALUES('Roberto', 43300.00, 102), ('Tom', 56100.00, 103), ('Yana', 60200.00, 101);

--02. One-To-Many Relationship
CREATE TABLE manufacturers (
	manufacturer_id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	established_on DATE NOT NULL
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE models (
	model_id INT(10) UNSIGNED NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	manufacturer_id INT(10) UNSIGNED NOT NULL,
	CONSTRAINT `fk_models_manufacturers` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturers` (`manufacturer_id`)
) ENGINE=InnoDB COLLATE=utf8_general_ci;
INSERT INTO `manufacturers`(`name`, `established_on`)
VALUES('BMW', '1916-03-01'), ('Tesla', '2003-01-01'), ('Lada', '1966-05-01');
INSERT INTO `models`(`model_id`, `name`, `manufacturer_id`)
VALUES(101, 'X1', 1), (102, 'i6', 1), (103, 'Model S', 2), (104, 'Model X', 2), (105, 'Model 3', 2), (106, 'Nova', 3);

--03. Many-To-Many Relationship
CREATE TABLE students (
	student_id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE exams (
	exam_id INT(10) UNSIGNED NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE students_exams (
	student_id INT(10) UNSIGNED NOT NULL,
	exam_id INT(10) UNSIGNED NOT NULL,
	CONSTRAINT `pk_student_id_exam_id` PRIMARY KEY (student_id, exam_id),
	CONSTRAINT `fk_student_id` FOREIGN KEY (student_id) REFERENCES students(student_id),
	CONSTRAINT `fk_exam_id` FOREIGN KEY (exam_id) REFERENCES exams(exam_id)
) ENGINE=InnoDB COLLATE=utf8_general_ci;
INSERT INTO `students`(`name`)
VALUES('Mila'), ('Toni'), ('Ron');
INSERT INTO `exams`(`exam_id`, `name`)
VALUES(101, 'Spring MVC'), (102, 'Neo4j'), (103, 'Oracle 11g');
INSERT INTO `students_exams`(`student_id`, `exam_id`)
VALUES(1, 101), (1, 102), (2, 101), (3, 103), (2, 102), (2, 103);

--04. Self-Referencing
CREATE TABLE teachers (
	teacher_id INT(10) PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	manager_id INT(10),
	CONSTRAINT `fk_manager_id_teacher_id` FOREIGN KEY (manager_id) REFERENCES teachers(teacher_id)
) ENGINE=InnoDB COLLATE=utf8_general_ci;
INSERT INTO `teachers`(`teacher_id`, `name`, `manager_id`)
VALUES(101, 'John', NULL), (106, 'Greta', 101), (105, 'Mark', 101),  (102, 'Maya', 106), (103, 'Silvia', 106), (104, 'Ted', 105);

--05. Online Store Database
CREATE TABLE item_types (
	item_type_id INT(11) NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE items (
	item_id INT(11) NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	item_type_id INT(11) NOT NULL,
	CONSTRAINT `fk_item_type_id` FOREIGN KEY (item_type_id) REFERENCES item_types(item_type_id)
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE cities (
	city_id INT(11) NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE customers (
	customer_id INT(11) NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	birthday DATE NOT NULL,
	city_id INT(11) NOT NULL,
	CONSTRAINT `fk_city_id` FOREIGN KEY (city_id) REFERENCES cities(city_id)
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE orders (
	order_id INT(11) NOT NULL PRIMARY KEY,
	customer_id INT(11) NOT NULL,
	CONSTRAINT `fk_customer_id` FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE order_items (
	order_id INT(11) NOT NULL,
	item_id INT(11) NOT NULL,
	CONSTRAINT `pk_order_id_item_id` PRIMARY KEY (order_id, item_id),
	CONSTRAINT `fk_order_id` FOREIGN KEY (order_id) REFERENCES orders(order_id),
	CONSTRAINT `fk_item_id` FOREIGN KEY (item_id) REFERENCES items(item_id)
) ENGINE=InnoDB COLLATE=utf8_general_ci;

--06. University Database
CREATE TABLE subjects (
	subject_id INT(11) NOT NULL PRIMARY KEY,
	subject_name VARCHAR(50) NOT NULL
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE majors (
	major_id INT(11) NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE students (
	student_id INT(11) NOT NULL PRIMARY KEY,
	student_number VARCHAR(12) NOT NULL,
	student_name VARCHAR(50) NOT NULL,
	major_id INT(11) NOT NULL,
	CONSTRAINT `fk_major_id` FOREIGN KEY (major_id) REFERENCES majors(major_id)
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE payments (
	payment_id INT(11) NOT NULL PRIMARY KEY,
	payment_date DATE NOT NULL,
	payment_amount DECIMAL(8,2) NOT NULL,
	student_id INT(11) NOT NULL,
	CONSTRAINT `fk_student_id` FOREIGN KEY (student_id) REFERENCES students(student_id)
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE agenda (
	student_id INT(11) NOT NULL,
	subject_id INT(11) NOT NULL,
	CONSTRAINT `pk_student_id_subject_id` PRIMARY KEY (student_id, subject_id),
	CONSTRAINT `fk_agenda_student_id` FOREIGN KEY (student_id) REFERENCES students(student_id),
	CONSTRAINT `fk_agenda_subject_id` FOREIGN KEY (subject_id) REFERENCES subjects(subject_id)
) ENGINE=InnoDB COLLATE=utf8_general_ci;

--09. Peaks in Rila 
SELECT mountains.mountain_range, peaks.peak_name, peaks.elevation AS peak_elevation
FROM peaks
INNER JOIN mountains ON mountains.id = peaks.mountain_id
WHERE mountains.mountain_range = 'Rila'
ORDER BY peak_elevation DESC;