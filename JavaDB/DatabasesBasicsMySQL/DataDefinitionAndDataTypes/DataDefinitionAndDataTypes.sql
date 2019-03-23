--01. Create Tables
--CREATE DATABASE minions;
CREATE TABLE minions (
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20),
    age INT(11)
);
CREATE TABLE towns (
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20)
);

--02. Alter Minions Table
ALTER TABLE minions
ADD town_id INT(11),
ADD CONSTRAINT fk_minions_towns FOREIGN KEY(town_id) REFERENCES towns(id);

--03. Insert Records in Both Tables
INSERT INTO towns (id, name) VALUES
	(1, "Sofia"),
	(2, "Plovdiv"),
	(3, "Varna");
INSERT INTO minions (id, name, age, town_id) VALUES
	(1, "Kevin", 22, 1),
	(2, "Bob", 15, 3),
	(3, "Steward", NULL, 2);

--04. Truncate Table Minions
TRUNCATE TABLE minions;

--05. Drop All Tables
DROP TABLE minions;
DROP TABLE towns;

--06. Create Table People
CREATE TABLE people (
	id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    picture BLOB NULL DEFAULT NULL,
	height FLOAT(3,2) UNSIGNED NULL DEFAULT NULL,
	weight FLOAT(5,2) UNSIGNED NULL DEFAULT NULL,
	gender ENUM('m', 'f') NOT NULL,
	birthdate DATE NOT NULL,
	biography LONGTEXT NULL DEFAULT NULL)
	ENGINE=InnoDB COLLATE=utf8_general_ci;
INSERT INTO people (name, height, weight, gender, birthdate) VALUES
	('John', 1.80, 80, 'm', '1987-03-01'),
	('Jessica', 1.65, 56, 'f', '1993-03-01'),
	('Peter', 1.85, 90, 'm', '1990-03-01'),
	('Barbara', 1.70, 60, 'f', '1994-03-01'),
	('George', 1.76, 80, 'm', '1991-03-01');

--07. Create Table Users
CREATE TABLE users (
	id BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(26) NOT NULL,
	profile_picture BLOB NULL DEFAULT NULL,
	last_login_time TIMESTAMP NULL DEFAULT NULL,
	is_deleted BIT(1) NULL DEFAULT 0)
	ENGINE=InnoDB COLLATE=utf8_general_ci;
INSERT INTO users (username, password) VALUES
	('pesho', '123456'),
	('gosho', '123456'),
	('stamat', '123456'),
	('stavri', '123456'),
	('manol', '123456');

--08. Change Primary Key 
ALTER TABLE users MODIFY id BIGINT NOT NULL;
ALTER TABLE users DROP PRIMARY KEY;
ALTER TABLE users ADD PRIMARY KEY `pk_users` (id, username);

--9. Set Default Value of a Field 
ALTER TABLE users MODIFY last_login_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL;

--10. Set Unique Field 
ALTER TABLE users DROP PRIMARY KEY, ADD PRIMARY KEY `pk_users` (id);
ALTER TABLE users MODIFY username VARCHAR(30) UNIQUE NOT NULL;

--11. Movies Database 
CREATE TABLE directors (
	id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    director_name VARCHAR(50) NOT NULL,
    notes TEXT) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE genres (
	id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    genre_name VARCHAR(50) NOT NULL,
    notes TEXT) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE categories (
	id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(50) NOT NULL,
    notes TEXT) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE movies (
	id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
	director_id INT(11) UNSIGNED,
	copyright_year YEAR NOT NULL,
	length TIME NOT NULL,
	genre_id INT(11) UNSIGNED,
	category_id INT(11) UNSIGNED,
	rating DECIMAL(2,1) UNSIGNED,
    notes TEXT) ENGINE=InnoDB COLLATE=utf8_general_ci;
INSERT INTO directors (director_name) VALUES
	('Kevin'),
	('John'),
	('Peter'),
	('George'),
	('Ben');
INSERT INTO genres (genre_name) VALUES
	('Action'),
	('Drama'),
	('Horror'),
	('Sci-Fi'),
	('Comedy');
INSERT INTO categories (category_name) VALUES
	('Category 1'),
	('Category 2'),
	('Category 3'),
	('Category 4'),
	('Category 5');
INSERT INTO movies (title, director_id, copyright_year, length, genre_id, category_id) VALUES
	('Jurassic Park', 1, 1993, '020700', 1, 1),
	('A.I. Artificial Intelligence', 1, 2001, '022600', 1, 1),
	('Taxi Driver', 2, 1976, '015400', 5, 1),
	('A Space Odyssey', 4, 1968,'022900', 1, 1),
	('Reservoir Dogs', 5, 1992, '013900', 2, 1);

--12. Car Rental Database 	
CREATE TABLE categories (
	id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR(50) NOT NULL,
	daily_rate DOUBLE,
	weekly_rate DOUBLE,
	monthly_rate DOUBLE,
    weekend_rate DOUBLE);
CREATE TABLE cars (
	id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    plate_number VARCHAR(10) NOT NULL,
	make VARCHAR(50) NOT NULL,
	model VARCHAR(50) NOT NULL,
	car_year YEAR NOT NULL,
	category_id INT(11) UNSIGNED NOT NULL,
	doors TINYINT(1) NOT NULL,
	picture BLOB NULL DEFAULT NULL,
	car_condition VARCHAR(50),
    available BIT(1) NOT NULL DEFAULT 1);
CREATE TABLE employees (
	id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	title VARCHAR(50) NOT NULL,
	notes TEXT);
CREATE TABLE customers (
	id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    driver_licence_number VARCHAR(50) NOT NULL,
	full_name VARCHAR(50) NOT NULL,
	address VARCHAR(50) NOT NULL,
	city VARCHAR(50) NOT NULL,
	zip_code VARCHAR(50) NOT NULL,
	notes TEXT);
CREATE TABLE rental_orders (
	id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    employee_id INT(11) UNSIGNED NOT NULL,
	customer_id INT(11) UNSIGNED NOT NULL,
	car_id INT(11) UNSIGNED NOT NULL,
	car_condition VARCHAR(50),
	tank_level FLOAT(5,2) UNSIGNED NOT NULL,
	kilometrage_start INT(11) UNSIGNED NOT NULL,
	kilometrage_end INT(11) UNSIGNED NOT NULL,
	total_kilometrage INT(11) UNSIGNED NOT NULL,
	start_date DATE NOT NULL,
	end_date DATE NOT NULL,
	total_days INT(3) NOT NULL,
	rate_applied DECIMAL NOT NULL,
	tax_rate DECIMAL NOT NULL,
	order_status VARCHAR(10),
	notes TEXT);
INSERT INTO categories(category, daily_rate, weekly_rate, monthly_rate, weekend_rate)
	VALUES
	('Standard', 85.20, 70.00, 67.50, 90.00),
	('Improved', 90.00, 85.00, 80.00, 95.00),
	('Luxory', 120.00, 112.56, 105.50, 130.00);
INSERT INTO cars (plate_number, make, model, car_year, category_id, doors, car_condition) 
	VALUES
	('SF-1234-BB', 'BMW', 'model', 2010, 1, 2, 'Excelend'),
	('BN-3210-KK', 'Ford', 'model', 2010, 2, 4, 'Excelend'),
	('SF-1234-AA', 'OPEL', 'model', 2010, 3, 2, 'Excelend');
INSERT INTO employees (first_name, last_name, title) 
	VALUES
	('Ivan', 'Petrov','Salesman'),
	('Petar', 'Ivnanov','Salesman'),
	('Georgi', 'Stamenov','Manager');
INSERT INTO customers (driver_licence_number, full_name, address, city, zip_code) 
	VALUES
	('321654987', 'Grigor Atanasov', 'Tsarigradsko shose 192', 'Sofia', '1000'),
	('987654321', 'Stanimir Laskov', 'Pobeda 13', 'Sofia', '1000'),
	('354813572', 'Marting Kisiov', 'Malina 32', 'Sofia', '1000');
INSERT INTO rental_orders (
	employee_id, 
	customer_id, 
	car_id, 
	car_condition, 
	tank_level, 
	kilometrage_start, 
	kilometrage_end, 
	total_kilometrage,
	start_date, 
	end_date, 
	total_days,
	rate_applied, 
	tax_rate, 
	order_status)
	VALUES
	(1, 1, 1, 'Excelent', 34.00, 432122, 432231, 109, '2018-01-20', '2018-01-28', 28, 90.00, 10.3, 3),
	(1, 2, 2, 'Excelent', 34.00, 432122, 432231, 109, '2018-01-20', '2018-01-28', 28, 90.00, 10.3, 3),
	(2, 3, 3, 'Excelent', 34.00, 432122, 432231, 109, '2018-01-26', '2018-01-29', 28, 90.00, 10.3, 3);

--13. Hotel Database 
CREATE TABLE employees (
	id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	title VARCHAR(50) NOT NULL,
	notes TEXT);
CREATE TABLE customers (
	account_number VARCHAR(30) UNIQUE PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	phone_number VARCHAR(50) NOT NULL,
	emergency_name VARCHAR(50) NOT NULL,
	emergency_number VARCHAR(50) NOT NULL,
	notes TEXT);
CREATE TABLE room_status (
	room_status VARCHAR(30) PRIMARY KEY NOT NULL, 
	notes TEXT);
CREATE TABLE room_types (
	room_type VARCHAR(30) PRIMARY KEY NOT NULL, 
	notes TEXT);
CREATE TABLE bed_types (
	bed_type VARCHAR(30) PRIMARY KEY NOT NULL, 
	notes TEXT);
CREATE TABLE rooms (
	room_number VARCHAR(10) UNIQUE PRIMARY KEY,
	room_type VARCHAR(30) NOT NULL,
	bed_type VARCHAR(30) NOT NULL,
	rate DECIMAL(10,2) NOT NULL,
	room_status VARCHAR(30) NOT NULL, 
	notes TEXT);
CREATE TABLE payments (
	id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	employee_id INT(11) UNSIGNED NOT NULL,
	payment_date DATE NOT NULL,
	account_number VARCHAR(30) NOT NULL,
	first_date_occupied DATE NOT NULL,
	last_date_occupied DATE NOT NULL,
	total_days INT(3) NOT NULL,
	amount_charged DECIMAL NOT NULL,
	tax_rate DECIMAL NOT NULL,
	tax_amount DECIMAL NOT NULL,
	payment_total DECIMAL NOT NULL,
	notes TEXT);
CREATE TABLE occupancies (
	id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	employee_id INT(11) UNSIGNED NOT NULL,
	date_occupied DATE NOT NULL,
	account_number VARCHAR(30) NOT NULL,
	room_number VARCHAR(10) NOT NULL,
	rate_applied VARCHAR(30) NOT NULL,
	phone_charge VARCHAR(50) NOT NULL,
	notes TEXT);
INSERT INTO employees (first_name, last_name, title, notes)
VALUES
	('Ivan', 'Petrov','Hotel Manager', 'New in the business'),
	('Petar', 'Ivnanov','Receptionist', NULL),
	('Georgi', 'Stamenov','Chambermaid', NULL);
INSERT INTO customers (account_number, first_name, last_name, phone_number, emergency_name, emergency_number, notes)
VALUES
	('321654987BUGBI43', 'Grigor', 'Atanasov', '+359 888 888 888', 'None', '1000', NULL),
	('987654321ASPN332', 'Stanimir', 'Laskov', '+359 888 888 882', 'None', '1000', NULL),
	('354813572BUIGBI1', 'Marting', 'Kisiov', '+359 888 777 666', 'None', '1000', NULL);
INSERT INTO room_status (room_status, notes)
VALUES
	('Ocuppied', NULL),
	('Ready', NULL),
	('For service', NULL);
INSERT INTO room_types (room_type, notes)
VALUES
	('Standard', NULL),
	('Apartment', NULL),
	('Extra', NULL);
INSERT INTO bed_types (bed_type, notes)
VALUES
	('Single', NULL),
	('Double', NULL),
	('Round', NULL);
INSERT INTO rooms (room_number, room_type, bed_type, rate, room_status, notes)
VALUES
	('A - 101', 'Standard', 'Single', 2, 'Ready', NULL),
	('A - 102', 'Standard', 'Single', 5, 'Ready', NULL),
	('A - 103', 'Standard', 'Single', 8, 'Ready', NULL);
INSERT INTO payments (employee_id, payment_date, account_number, first_date_occupied, last_date_occupied, total_days, amount_charged, tax_rate, tax_amount, payment_total, notes) VALUES
	(1, '2018-01-25', 'S090921', '2018-01-10', '2018-01-25', 15, 263.15, 10.5, 27.63, 290.78, NULL),
	(2, '2018-01-25', 'S09095', '2018-01-10', '2018-01-25', 15, 263.15, 10.5, 27.63, 290.78, NULL),
	(3, '2018-01-25', 'S090971', '2018-01-10', '2018-01-25', 15, 263.15, 10.5, 27.63, 290.78, NULL);
INSERT INTO occupancies (employee_id, date_occupied, account_number, room_number, rate_applied, phone_charge, notes)
VALUES
	(2, '2018-01-15', 'S090921', 'A - 101', 85.50, 15.00, NULL),
	(2, '2018-01-15', 'S090921', 'A - 101', 85.50, 15.00, NULL),
	(2, '2018-01-15', 'S090921', 'A - 101', 85.50, 15.00, NULL);

--14. Create SoftUni Database 
CREATE TABLE towns (
	id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL
);
CREATE TABLE addresses (
	id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	address_text VARCHAR(50) NOT NULL,
	town_id INT(11) UNSIGNED NOT NULL,
	CONSTRAINT fk_town_id FOREIGN KEY (town_id) REFERENCES towns(id)
);
CREATE TABLE departments (
	id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL
);
CREATE TABLE employees (
	id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(50) NOT NULL,
	middle_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	job_title VARCHAR(50) NOT NULL,
	department_id INT(11) UNSIGNED NOT NULL,
	hire_date DATE NOT NULL,
	salary DECIMAL(10,2) NOT NULL,
	address_id INT(11) UNSIGNED NOT NULL,
	CONSTRAINT fk_department_id FOREIGN KEY (department_id) REFERENCES departments(id),
	CONSTRAINT fk_address_id FOREIGN KEY (address_id) REFERENCES addresses(id)
);

--16. Basic Insert
INSERT INTO towns (name)
	VALUES ('Sofia'), ('Plovdiv'), ('Varna'), ('Burgas');
INSERT INTO departments (name)
	VALUES ('Engineering'), ('Sales'), ('Marketing'), ('Software Development'), ('Quality Assurance');
INSERT INTO employees (first_name, middle_name, last_name, job_title, department_id, hire_date, salary)
	VALUES ("Ivan", "Ivanov", "Ivanov", ".NET Developer", 4, "2013-02-01", 3500.00),
	("Petar", "Petrov", "Petrov", "Senior Engineer", 1, "2004-03-02", 4000.00),
	("Maria", "Petrova", "Ivanova", "Intern", 5, "2016-08-28", 525.25),
	("Georgi", "Terziev", "Ivanov", "CEO", 2, "2007-12-09", 3000.00),
	("Peter", "Pan", "Pan", "Intern", 3, "2016-08-28", 599.88);

--17. Basic Select All Fields
SELECT * FROM towns;
SELECT * FROM departments;
SELECT * FROM employees;

--18. Basic Select All Fields and Order Them
SELECT * FROM towns
ORDER BY name ASC;
SELECT * FROM departments
ORDER BY name ASC;
SELECT * FROM employees
ORDER BY salary DESC;

--19. Basic Select Some Fields 
SELECT name FROM towns
ORDER BY name ASC;
SELECT name FROM departments
ORDER BY name ASC;
SELECT first_name, last_name, job_title, salary FROM employees
ORDER BY salary DESC;

--20. Increase Employees Salary 
UPDATE employees 
SET salary = salary * 1.10;
SELECT salary FROM employees;

--21. Decrease Tax Rate 
UPDATE payments 
SET tax_rate = tax_rate * 0.97;
SELECT tax_rate FROM payments;

--22. Delete All Records 
DELETE FROM occupancies;