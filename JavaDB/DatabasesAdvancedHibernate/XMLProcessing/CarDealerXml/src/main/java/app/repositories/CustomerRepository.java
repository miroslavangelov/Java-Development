package app.repositories;

import app.domain.dtos.CustomerWithSalesDto;
import app.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "SELECT c.* FROM customers AS c\n" +
            "ORDER BY c.birth_date, c.is_young_driver;", nativeQuery = true)
    List<Customer> getCustomersOrderedByBirthDate();

    @Query(value = "SELECT DISTINCT c.* FROM customers AS c INNER JOIN sales AS s ON c.id = s.customer_id;", nativeQuery = true)
    List<Customer> getAllCustomerWithSales();
}
