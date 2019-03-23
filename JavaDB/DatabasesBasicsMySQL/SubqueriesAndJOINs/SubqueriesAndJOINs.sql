--01. Employee Address 
SELECT employees.employee_id, employees.job_title, employees.address_id, addresses.address_text
FROM employees
INNER JOIN addresses ON employees.address_id = addresses.address_id
ORDER BY employees.address_id
LIMIT 5;

--02. Addresses with Towns
SELECT employees.first_name, employees.last_name, towns.name, addresses.address_text
FROM employees
INNER JOIN addresses ON employees.address_id = addresses.address_id
INNER JOIN towns ON towns.town_id = addresses.town_id
ORDER BY employees.first_name, employees.last_name
LIMIT 5;

--03. Sales Employee
SELECT employees.employee_id, employees.first_name, employees.last_name, departments.name
FROM employees
INNER JOIN departments ON employees.department_id = departments.department_id
WHERE departments.name = 'Sales'
ORDER BY employees.employee_id DESC;

--04. Employee Departments
SELECT employees.employee_id, employees.first_name, employees.salary, departments.name
FROM employees
INNER JOIN departments ON employees.department_id = departments.department_id
WHERE employees.salary > 15000
ORDER BY departments.department_id DESC
LIMIT 5;

--05. Employees Without Project
SELECT employees.employee_id, employees.first_name
FROM employees
LEFT JOIN employees_projects ON employees.employee_id = employees_projects.employee_id
WHERE employees_projects.project_id IS NULL
ORDER BY employees.employee_id DESC
LIMIT 3;

--06. Employees Hired After 
SELECT employees.first_name, employees.last_name, employees.hire_date, departments.name AS `dept_name`
FROM employees
INNER JOIN departments ON employees.department_id = departments.department_id
WHERE DATE(employees.hire_date) > '1999-01-01'
AND departments.name IN ('Sales', 'Finance')
ORDER BY employees.hire_date;

--07. Employees with Project
SELECT employees.employee_id, employees.first_name, projects.name
FROM employees
INNER JOIN employees_projects ON employees.employee_id = employees_projects.employee_id
INNER JOIN projects ON employees_projects.project_id = projects.project_id
WHERE DATE(projects.start_date) > '2002-08-13'
AND projects.end_date IS NULL
ORDER BY employees.first_name, projects.name
LIMIT 5;

--08. Employee 24 
SELECT employees.employee_id, employees.first_name,
IF(YEAR(projects.start_date) >= 2005, NULL, projects.name) AS project_name
FROM employees
INNER JOIN employees_projects ON employees.employee_id = employees_projects.employee_id
INNER JOIN projects ON employees_projects.project_id = projects.project_id
WHERE employees.employee_id = 24
ORDER BY project_name;

--09. Employee Manager
SELECT empl1.employee_id, empl1.first_name, empl1.manager_id, empl2.first_name AS manager_name
FROM employees AS empl1
INNER JOIN employees empl2 ON empl1.manager_id = empl2.employee_id
WHERE empl1.manager_id IN (3, 7)
ORDER BY empl1.first_name;

--10. Employee Summary
SELECT empl1.employee_id, CONCAT(empl1.first_name, ' ', empl1.last_name) AS employee_name,
CONCAT(empl2.first_name, ' ', empl2.last_name) AS manager_name, departments.name AS department_name
FROM employees AS empl1
INNER JOIN employees empl2 ON empl1.manager_id = empl2.employee_id
INNER JOIN departments ON empl1.department_id = departments.department_id
WHERE empl1.manager_id IS NOT NULL
ORDER BY empl1.employee_id
LIMIT 5;

--11. Min Average Salary
SELECT MIN(average_salaries.average_salary) AS min_average_salary
FROM (SELECT AVG(salary) AS average_salary
FROM employees
GROUP BY department_id) AS average_salaries;

--12. Highest Peaks in Bulgaria
SELECT countries.country_code, mountains.mountain_range, peaks.peak_name, peaks.elevation
FROM countries
INNER JOIN mountains_countries ON countries.country_code = mountains_countries.country_code
INNER JOIN mountains ON mountains_countries.mountain_id = mountains.id
INNER JOIN peaks ON peaks.mountain_id = mountains.id
WHERE countries.country_code = 'BG'
AND peaks.elevation > 2835
ORDER BY peaks.elevation DESC;

--13. Count Mountain Ranges
SELECT countries.country_code, COUNT(mountains.mountain_range) AS mountain_range
FROM countries
INNER JOIN mountains_countries ON countries.country_code = mountains_countries.country_code
INNER JOIN mountains ON mountains_countries.mountain_id = mountains.id
WHERE countries.country_code IN ('BG', 'RU', 'US')
GROUP BY countries.country_code
ORDER BY mountain_range DESC, countries.country_code;

--14. Countries with Rivers
SELECT countries.country_name, rivers.river_name
FROM countries
INNER JOIN continents ON continents.continent_code = countries.continent_code
LEFT JOIN countries_rivers ON countries.country_code = countries_rivers.country_code
LEFT JOIN rivers ON countries_rivers.river_id = rivers.id
WHERE continents.continent_name = 'Africa'
ORDER BY countries.country_name
LIMIT 5;

--15. Continents and Currencies
SELECT c1.continent_code, c1.currency_code, COUNT(c1.currency_code) AS `currency_usage`
FROM countries AS c1
GROUP BY c1.continent_code, c1.currency_code
HAVING `currency_usage` = (
	SELECT COUNT(c2.currency_code) AS `count`
	FROM countries AS c2
	WHERE c2.continent_code = c1.continent_code
	GROUP BY c2.currency_code
	ORDER BY `count` DESC
	LIMIT 1
)
AND `currency_usage` > 1
ORDER BY c1.continent_code, c1.currency_code;

--16. Countries without any Mountains 
SELECT COUNT(*) AS country_count
FROM countries
LEFT JOIN mountains_countries ON countries.country_code = mountains_countries.country_code
WHERE mountains_countries.mountain_id IS NULL;

--17. Highest Peak and Longest River by Country
SELECT countries.country_name,
MAX(peaks.elevation) AS highest_peak_elevation,
MAX(rivers.length) AS longest_river_length
FROM countries
LEFT JOIN mountains_countries ON countries.country_code = mountains_countries.country_code
LEFT JOIN mountains ON mountains_countries.mountain_id = mountains.id
LEFT JOIN peaks ON peaks.mountain_id = mountains.id
LEFT JOIN countries_rivers ON countries.country_code = countries_rivers.country_code
LEFT JOIN rivers ON countries_rivers.river_id = rivers.id
GROUP BY countries.country_name
ORDER BY highest_peak_elevation DESC, longest_river_length DESC, countries.country_name
LIMIT 5;