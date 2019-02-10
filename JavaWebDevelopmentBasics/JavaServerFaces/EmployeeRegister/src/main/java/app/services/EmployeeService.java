package app.services;

import app.domain.models.service.EmployeeServiceModel;

import java.util.List;

public interface EmployeeService {
    void saveEmployee(EmployeeServiceModel employeeServiceModel);

    List<EmployeeServiceModel> findAllEmployees();

    void removeEmployee(String id);
}
