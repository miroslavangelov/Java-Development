package app.repositories;

import app.domain.entities.Employee;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final EntityManager entityManager;

    public EmployeeRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("employee_register")
                .createEntityManager();
    }

    @Override
    public Employee save(Employee entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public Employee findById(String id) {
        this.entityManager.getTransaction().begin();
        Employee employee = this.entityManager.createQuery("SELECT e FROM employees AS e WHERE e.id = :id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return employee;
    }

    @Override
    public List<Employee> findAll() {
        this.entityManager.getTransaction().begin();
        List<Employee> employees = this.entityManager.createQuery("SELECT e FROM employees AS e", Employee.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return employees;
    }

    @Override
    public void deleteById(String id) {
        this.entityManager.getTransaction().begin();
        this.entityManager.createQuery("DELETE FROM employees AS e WHERE e.id = :id", Employee.class)
                .setParameter("id", id)
                .executeUpdate();
        this.entityManager.getTransaction().commit();
    }
}
