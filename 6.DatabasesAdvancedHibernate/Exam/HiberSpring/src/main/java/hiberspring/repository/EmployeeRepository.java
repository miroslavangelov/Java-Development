package hiberspring.repository;

import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByCard(EmployeeCard employeeCard);

    @Query(
            "SELECT e\n" +
            "FROM hiberspring.domain.entities.Employee AS e\n" +
            "INNER JOIN e.branch AS b\n" +
            "INNER JOIN b.products AS p\n" +
            "GROUP BY e.id\n" +
            "ORDER BY e.firstName, e.lastName, LENGTH(e.position) DESC"
    )
    List<Employee> exportEmployees();
}
