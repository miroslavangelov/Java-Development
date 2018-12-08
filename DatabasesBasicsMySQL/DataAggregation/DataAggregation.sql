--01. Records’ Count
SELECT COUNT(id) AS `count` FROM wizzard_deposits;

--02. Longest Magic Wand
SELECT MAX(magic_wand_size) AS `longest_magic_wand` FROM wizzard_deposits;

--03. Longest Magic Wand per Deposit Groups
SELECT deposit_group,
MAX(magic_wand_size) AS `longest_magic_wand`
FROM wizzard_deposits
GROUP BY deposit_group
ORDER BY `longest_magic_wand`, deposit_group;

--04. Smallest Deposit Group per Magic Wand Size
SELECT deposit_group
FROM wizzard_deposits
GROUP BY deposit_group
ORDER BY AVG(magic_wand_size)
LIMIT 1;

--05. Deposits Sum
SELECT deposit_group, SUM(deposit_amount) AS `total_sum`
FROM wizzard_deposits
GROUP BY deposit_group
ORDER BY `total_sum`;

--06. Deposits Sum for Ollivander Family
SELECT deposit_group, SUM(deposit_amount) AS `total_sum`
FROM wizzard_deposits
WHERE magic_wand_creator = 'Ollivander family'
GROUP BY deposit_group
ORDER BY deposit_group;

--07. Deposits Filter
SELECT deposit_group, SUM(deposit_amount) AS `total_sum`
FROM wizzard_deposits
WHERE magic_wand_creator = 'Ollivander family'
GROUP BY deposit_group
HAVING `total_sum` < 150000
ORDER BY `total_sum` DESC;

--08. Deposit Charge
SELECT deposit_group, magic_wand_creator, MIN(deposit_charge) AS min_deposit_charge
FROM wizzard_deposits
GROUP BY deposit_group, magic_wand_creator
ORDER BY magic_wand_creator, deposit_group;

--09. Age Groups
SELECT
CASE
    WHEN age <= 10 THEN '[0-10]'
    WHEN age <= 20 THEN '[11-20]'
	WHEN age <= 30 THEN '[21-30]'
	WHEN age <= 40 THEN '[31-40]'
	WHEN age <= 50 THEN '[41-50]'
	WHEN age <= 60 THEN '[51-60]'
	WHEN age > 60 THEN '[61+]'
END AS `age_group`,
COUNT(id) AS `wizard_count`
FROM wizzard_deposits
GROUP BY `age_group`
ORDER BY `age_group`;

--10. First Letter
SELECT LEFT(first_name, 1) AS `first_letter`
FROM wizzard_deposits
WHERE deposit_group = 'Troll Chest'
GROUP BY `first_letter`
ORDER BY `first_letter`;

--11. Average Interest
SELECT deposit_group, is_deposit_expired,
AVG(deposit_interest) AS `average_interest`
FROM wizzard_deposits
WHERE deposit_start_date > '1985-01-01'
GROUP BY deposit_group, is_deposit_expired
ORDER BY deposit_group DESC, is_deposit_expired;

--12. Rich Wizard, Poor Wizard 
SELECT SUM(w.differences) AS sum_difference 
FROM (SELECT (w2.deposit_amount - w1.deposit_amount) AS differences
FROM wizzard_deposits AS w1 
INNER join wizzard_deposits AS w2
on w1.id = w2.id+1) AS w

--13. Employees Minimum Salaries
SELECT department_id, MIN(salary) AS `minimum_salary`
FROM employees
WHERE hire_date > '2000-01-01'
AND department_id IN (2, 5, 7)
GROUP BY department_id
ORDER BY department_id;

--14. Employees Average Salaries
CREATE TABLE high_paid_employees
SELECT * FROM employees
WHERE salary > 30000;
DELETE FROM high_paid_employees
WHERE manager_id = 42;
UPDATE high_paid_employees 
SET salary = salary + 5000
WHERE department_id = 1;
SELECT department_id, AVG(salary) AS avg_salary
FROM high_paid_employees
GROUP BY department_id
ORDER BY department_id;

--15. Employees Maximum Salaries
SELECT department_id, MAX(salary) AS `max_salary`
FROM employees
GROUP BY department_id
HAVING `max_salary` NOT BETWEEN 30000 AND 70000
ORDER BY department_id;

--16. Employees Count Salaries
SELECT COUNT(employee_id) AS `count`
FROM employees
WHERE manager_id IS NULL;

--17. 3rd Highest Salary
SELECT department_id,
(SELECT DISTINCT (empl2.salary) FROM employees AS empl2
WHERE empl1.department_id = empl2.department_id
ORDER BY empl2.salary DESC
LIMIT 2,1)
AS third_highest_salary
FROM employees AS empl1
GROUP BY empl1.department_id
HAVING third_highest_salary IS NOT NULL
ORDER BY empl1.department_id;

--18. Salary Challenge
SELECT empl1.first_name, empl1.last_name, empl1.department_id
FROM employees AS empl1
INNER JOIN (
	SELECT empl2.department_id, AVG(empl2.salary) AS average_salary
	FROM employees AS empl2
	GROUP BY empl2.department_id
) AS `average_salaries` ON empl1.department_id = average_salaries.department_id
WHERE empl1.salary > average_salaries.average_salary
ORDER BY empl1.department_id
LIMIT 10;

--19. Departments Total Salaries 
SELECT department_id, SUM(salary) AS total_salary
FROM employees
GROUP BY department_id
ORDER BY department_id; 