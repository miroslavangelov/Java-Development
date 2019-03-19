--00. Table Design
CREATE TABLE planets (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE spaceports (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
	planet_id INT,
	CONSTRAINT `fk_spaceports_planets` FOREIGN KEY (planet_id) REFERENCES planets(id)
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE spaceships (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
	manufacturer VARCHAR(30) NOT NULL,
	light_speed_rate INT DEFAULT 0
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE colonists (
	id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
	ucn CHAR(10) UNIQUE NOT NULL,
	birth_date DATE NOT NULL
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE journeys (
    id INT PRIMARY KEY AUTO_INCREMENT,
	journey_start DATETIME NOT NULL,
	journey_end DATETIME NOT NULL,
	purpose ENUM('Medical', 'Technical', 'Educational', 'Military'),
	destination_spaceport_id INT,
	spaceship_id INT,
	CONSTRAINT `fk_journeys_spaceports` FOREIGN KEY (destination_spaceport_id) REFERENCES spaceports(id),
	CONSTRAINT `fk_journeys_spaceships` FOREIGN KEY (spaceship_id) REFERENCES spaceships(id)
) ENGINE=InnoDB COLLATE=utf8_general_ci;
CREATE TABLE travel_cards (
    id INT PRIMARY KEY AUTO_INCREMENT,
	card_number CHAR(10) UNIQUE NOT NULL,
	job_during_journey ENUM('Pilot', 'Engineer', 'Trooper', 'Cleaner', 'Cook'),
	colonist_id INT,
	journey_id INT,
	CONSTRAINT `fk_travel_cards_colonists` FOREIGN KEY (colonist_id) REFERENCES colonists(id),
	CONSTRAINT `fk_travel_cards_journeys` FOREIGN KEY (journey_id) REFERENCES journeys(id)
) ENGINE=InnoDB COLLATE=utf8_general_ci;

--01. Insert
INSERT INTO travel_cards(card_number, job_during_journey, colonist_id, journey_id)
SELECT
	(CASE WHEN c.birth_date > '1980-01-01' 
	THEN CONCAT(YEAR(c.birth_date), DAY(c.birth_date), LEFT(c.ucn , 4))
	ELSE CONCAT(YEAR(c.birth_date), MONTH(c.birth_date), RIGHT(c.ucn , 4))
	END) AS card_number,
	(CASE WHEN c.id % 2 = 0 THEN 'Pilot'
	WHEN c.id % 3 = 0 THEN 'Cook'
	ELSE 'Engineer'
	END) AS job_during_journey,
	c.id,
	LEFT(c.ucn, 1) AS journey_id
FROM colonists AS c
WHERE c.id BETWEEN 96 AND 100;

--02. Update
UPDATE journeys
SET purpose = (
	CASE WHEN id % 2 = 0 THEN 'Medical'
	WHEN id % 3 = 0 THEN 'Technical'
	WHEN id % 5 = 0 THEN 'Educational'
	WHEN id % 7 = 0 THEN 'Military'
	ELSE purpose
	END
);

--03. Delete
DELETE colonists FROM colonists
LEFT JOIN travel_cards AS tc ON tc.colonist_id = colonists.id
WHERE tc.journey_id IS NULL;

--04. Extract all travel cards
SELECT card_number, job_during_journey
FROM travel_cards
ORDER BY card_number;

--05. Extract all colonists 
SELECT id, CONCAT(first_name, ' ', last_name) AS full_name, ucn
FROM colonists
ORDER BY first_name, last_name, id;

--06. Extract all military journeys
SELECT id, journey_start, journey_end
FROM journeys
WHERE purpose = 'Military'
ORDER BY journey_start;

--07. Extract all pilots
SELECT c.id, CONCAT(first_name, ' ', last_name) AS full_name
FROM colonists AS c
INNER JOIN travel_cards AS tc ON tc.colonist_id = c.id
WHERE tc.job_during_journey = 'Pilot'
ORDER BY c.id;

--08. Count all colonists
SELECT COUNT(c.id) AS count
FROM colonists AS c
INNER JOIN travel_cards AS tc ON tc.colonist_id = c.id
INNER JOIN journeys AS j ON tc.journey_id = j.id
WHERE j.purpose = 'Technical';

--09. Extract the fastest spaceship
SELECT spaceships.name AS spaceship_name, spaceports.name AS spaceport_name
FROM spaceships
INNER JOIN journeys ON journeys.spaceship_id = spaceships.id
INNER JOIN spaceports ON spaceports.id = journeys.destination_spaceport_id
ORDER BY spaceships.light_speed_rate DESC
LIMIT 1;

--10. Extract - pilots younger than 30 years
SELECT s.name, s.manufacturer
FROM travel_cards AS tc
INNER JOIN colonists AS c ON tc.colonist_id = c.id
INNER JOIN journeys AS j ON j.id = tc.journey_id
INNER JOIN spaceships AS s ON j.spaceship_id = s.id
WHERE c.birth_date > '1989-01-01'
AND tc.job_during_journey = 'Pilot'
ORDER BY s.name;

--11. Extract all educational mission planets and spaceports
SELECT p.name AS planet_name, sp.name AS spaceport_name
FROM planets AS p
INNER JOIN spaceports AS sp ON sp.planet_id = p.id
INNER JOIN journeys AS j ON j.destination_spaceport_id = sp.id
WHERE j.purpose = 'Educational'
ORDER BY spaceport_name DESC;

--12. Extract all planets and their journey count
SELECT p.name AS planet_name, COUNT(j.id) AS journeys_count
FROM journeys AS j
LEFT JOIN spaceports AS sp ON sp.id = j.destination_spaceport_id
LEFT JOIN planets AS p ON p.id = sp.planet_id
GROUP BY p.id
ORDER BY journeys_count DESC, p.name;

--13. Extract the shortest journey
SELECT j.id, p.name AS planet_name, sp.name AS spaceport_name, j.purpose AS journey_purpose
FROM journeys AS j
INNER JOIN spaceports AS sp ON sp.id = j.destination_spaceport_id
INNER JOIN planets AS p ON p.id = sp.planet_id
ORDER BY TIMESTAMPDIFF(SECOND, j.journey_start, j.journey_end)
LIMIT 1;

--14. Extract the less popular job
SELECT tc.job_during_journey AS job_name
FROM travel_cards AS tc
WHERE tc.journey_id = (
		SELECT j.id
		FROM journeys AS j
		ORDER BY TIMESTAMPDIFF(SECOND, j.journey_start, j.journey_end) DESC
		LIMIT 1
)
GROUP BY tc.job_during_journey
ORDER BY COUNT(tc.job_during_journey)
LIMIT 1;

--15. Get colonists count
CREATE FUNCTION udf_count_colonists_by_destination_planet(planet_name VARCHAR (30))
RETURNS INT
BEGIN
	DECLARE colonists_count INT;
	SET colonists_count := (SELECT COUNT(tc.colonist_id)
		FROM travel_cards AS tc
		INNER JOIN journeys AS j ON j.id = tc.journey_id
		INNER JOIN spaceports AS sp ON sp.id = j.destination_spaceport_id
		INNER JOIN planets AS p ON p.id = sp.planet_id
		WHERE p.name = planet_name);
	RETURN colonists_count;
END

--16. Modify spaceship
CREATE PROCEDURE udp_modify_spaceship_light_speed_rate(spaceship_name VARCHAR(50), light_speed_rate_increse INT(11))
BEGIN
	START TRANSACTION;
	IF (SELECT spaceships.id FROM spaceships WHERE spaceships.name = spaceship_name) IS NULL
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Spaceship you are trying to modify does not exists.';
		ROLLBACK;
	ELSE
		UPDATE spaceships
		SET light_speed_rate = light_speed_rate + light_speed_rate_increse
		WHERE spaceships.name = spaceship_name;
		COMMIT;
	END IF;
END