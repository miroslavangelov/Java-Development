package app.services;

import app.domain.entities.Employee;
import app.domain.models.service.EmployeeServiceModel;
import app.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Inject
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveEmployee(EmployeeServiceModel employeeServiceModel) {
        Employee employee = this.modelMapper.map(employeeServiceModel, Employee.class);
        this.employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeServiceModel> findAllEmployees() {
        List<Employee> employees = this.employeeRepository.findAll();
        List<EmployeeServiceModel> employeeServiceModels = new ArrayList<>();

        employees.forEach(employee -> {
            EmployeeServiceModel employeeServiceModel = this.modelMapper.map(employee, EmployeeServiceModel.class);
            employeeServiceModels.add(employeeServiceModel);
        });

        return employeeServiceModels;
    }

    @Override
    public void removeEmployee(String id) {
        this.employeeRepository.deleteById(id);
    }
}
