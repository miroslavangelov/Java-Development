--01. Employees with Salary Above 35000 
CREATE PROCEDURE usp_get_employees_salary_above_35000()
BEGIN
   SELECT first_name, last_name
   FROM employees
   WHERE salary > 35000
   ORDER BY first_name, last_name, employee_id;
END

--02. Employees with Salary Above Number
CREATE PROCEDURE usp_get_employees_salary_above(amount DECIMAL(19,4))
BEGIN
   SELECT first_name, last_name
   FROM employees
   WHERE salary >= amount
   ORDER BY first_name, last_name, employee_id;
END

--03. Town Names Starting With
CREATE PROCEDURE usp_get_towns_starting_with(startsWith VARCHAR(10))
BEGIN
   SELECT name
   FROM towns
   WHERE name LIKE CONCAT(startsWith, '%')
   ORDER BY name;
END

--04. Employees from Town
CREATE PROCEDURE usp_get_employees_from_town(town_name VARCHAR(50))
BEGIN
   SELECT employees.first_name, employees.last_name
   FROM employees
   INNER JOIN addresses ON addresses.address_id = employees.address_id
   INNER JOIN towns ON addresses.town_id = towns.town_id
   WHERE towns.name = town_name
   ORDER BY first_name, last_name, employee_id;
END

--05. Salary Level Function
CREATE FUNCTION ufn_get_salary_level(salary_amount DECIMAL(19,4))
RETURNS VARCHAR(10)
BEGIN
	DECLARE salary_level VARCHAR(10);
	SET salary_level := CASE
		WHEN salary_amount < 30000 THEN 'Low'
		WHEN salary_amount >= 30000 AND salary_amount <= 50000 THEN 'Average'
		WHEN salary_amount > 50000 THEN 'High'
	END;
	RETURN salary_level;
END

--06. Employees by Salary Level
CREATE FUNCTION ufn_get_salary_level(salary_amount DECIMAL(19,4))
RETURNS VARCHAR(10)
BEGIN
	DECLARE salary_level VARCHAR(10);
	SET salary_level := CASE
		WHEN salary_amount < 30000 THEN 'Low'
		WHEN salary_amount >= 30000 AND salary_amount <= 50000 THEN 'Average'
		WHEN salary_amount > 50000 THEN 'High'
	END;
	RETURN salary_level;
END;
CREATE PROCEDURE usp_get_employees_by_salary_level(salary_level VARCHAR(10))
BEGIN
   SELECT first_name, last_name
   FROM employees
   WHERE ufn_get_salary_level(salary) = salary_level
   ORDER BY first_name DESC, last_name DESC;
END

--07. Define Function
CREATE FUNCTION ufn_is_word_comprised(set_of_letters VARCHAR(50), word VARCHAR(50))
RETURNS BIT
BEGIN
	DECLARE isComprised BIT;
	SET isComprised := CASE
		WHEN word REGEXP CONCAT('^[', set_of_letters, ']+$') THEN 1
		ELSE 0
	END;
	RETURN isComprised;
END

--08. Find Full Name
CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
   SELECT CONCAT (first_name, ' ', last_name) AS full_name
   FROM account_holders
   ORDER BY full_name, id;
END

--9. People with Balance Higher Than
CREATE PROCEDURE usp_get_holders_with_balance_higher_than(amount DECIMAL(19,4))
BEGIN
   SELECT account_holders.first_name, account_holders.last_name
   FROM account_holders
   INNER JOIN accounts ON accounts.account_holder_id = account_holders.id
   GROUP BY account_holders.id
   HAVING SUM(accounts.balance) > amount
   ORDER BY accounts.id;
END

--10. Future Value Function
CREATE FUNCTION ufn_calculate_future_value(initial_sum DECIMAL(19,4), yearly_interest_rate DECIMAL(10,4), number_of_years INT)
RETURNS DOUBLE
BEGIN
	DECLARE future_value DOUBLE;
	SET future_value := initial_sum * POW((1 + yearly_interest_rate), number_of_years);
	RETURN future_value;
END

--11. Calculating Interest
CREATE FUNCTION ufn_calculate_future_value(initial_sum DECIMAL(19,4), yearly_interest_rate DECIMAL(10,4), number_of_years INT)
RETURNS DECIMAL(19, 4)
BEGIN
	DECLARE future_value DECIMAL(19, 4);
	SET future_value := initial_sum * POW((1 + yearly_interest_rate), number_of_years);
	RETURN future_value;
END;
CREATE PROCEDURE usp_calculate_future_value_for_account(person_account_id INT(11), interest_rate DECIMAL(10,4))
BEGIN
   SELECT accounts.id AS account_id, account_holders.first_name, account_holders.last_name,
   accounts.balance AS current_balance, ufn_calculate_future_value((SELECT balance FROM accounts WHERE id = person_account_id), interest_rate, 5) AS balance_in_5_years
   FROM accounts
   INNER JOIN account_holders ON accounts.account_holder_id = account_holders.id
   WHERE accounts.id = person_account_id;
END

--12. Deposit Money
CREATE PROCEDURE usp_deposit_money(account_id INT(11), money_amount DECIMAL(19,4))
BEGIN
	START TRANSACTION;
	UPDATE accounts AS a
	SET a.balance = a.balance + money_amount
	WHERE a.id = account_id;
	IF (money_amount < 0)
		THEN ROLLBACK;
	ELSE
		COMMIT;
	END IF;
END

--13. Withdraw Money
CREATE PROCEDURE usp_withdraw_money(account_id INT(11), money_amount DECIMAL(19,4))
BEGIN
	DECLARE current_balance DECIMAL(19, 4);
	START TRANSACTION;
	SET current_balance := (SELECT balance FROM accounts WHERE id = account_id) - money_amount;
	UPDATE accounts AS a
	SET a.balance = current_balance
	WHERE a.id = account_id;
	IF (money_amount < 0 OR current_balance < 0)
		THEN ROLLBACK;
	ELSE
		COMMIT;
	END IF;
END

--14. Money Transfer
CREATE PROCEDURE usp_transfer_money(from_account_id INT(11), to_account_id INT(11), money_amount DECIMAL(19,4))
BEGIN
	DECLARE from_current_balance DECIMAL(19, 4);
	START TRANSACTION;
	SET from_current_balance := (SELECT balance FROM accounts WHERE id = from_account_id) - money_amount;
	UPDATE accounts AS a
	SET a.balance = from_current_balance
	WHERE a.id = from_account_id;
	UPDATE accounts AS a
	SET a.balance = a.balance + money_amount
	WHERE a.id = to_account_id;
	IF (money_amount < 0
		OR from_account_id = to_account_id
		OR from_current_balance < 0
		OR (SELECT id FROM accounts WHERE id = from_account_id) IS NULL
		OR (SELECT id FROM accounts WHERE id = to_account_id) IS NULL)
		THEN ROLLBACK;
	ELSE
		COMMIT;
	END IF;
END

--15. Log Accounts Trigger
CREATE TABLE logs (
	log_id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	account_id INT(11) NOT NULL,
	old_sum DECIMAL(19, 4) NOT NULL,
	new_sum DECIMAL(19, 4) NOT NULL
);
CREATE TRIGGER log_new_value 
    BEFORE UPDATE ON accounts
    FOR EACH ROW 
BEGIN
    INSERT INTO logs(account_id, old_sum, new_sum)
	VALUES(OLD.id, OLD.balance, NEW.balance);
END

--16. Emails Trigger
CREATE TABLE logs (
	log_id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	account_id INT(11) NOT NULL,
	old_sum DECIMAL(19, 4) NOT NULL,
	new_sum DECIMAL(19, 4) NOT NULL
);
CREATE TABLE notification_emails (
	id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	recipient INT(11) NOT NULL,
	subject VARCHAR(50) NOT NULL,
	body TEXT NOT NULL
);
CREATE TRIGGER log_new_value 
    AFTER UPDATE ON accounts
    FOR EACH ROW 
BEGIN
    INSERT INTO logs(account_id, old_sum, new_sum)
	VALUES(OLD.id, OLD.balance, NEW.balance);
    INSERT INTO notification_emails(recipient, subject, body)
	VALUES(
		OLD.id,
		CONCAT('Balance change for account: ', OLD.id),
		CONCAT('On', DATE_FORMAT(NOW(), '%b %e %Y at %I:%i:%s %p'), ' your balance was changed from ', OLD.balance, ' to ', NEW.balance)
	);
END