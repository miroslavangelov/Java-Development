package app.web.beans;

import app.domain.models.service.EmployeeServiceModel;
import app.services.EmployeeService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class EmployeeListBean {
    private List<EmployeeServiceModel> employees;
    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public EmployeeListBean() {

    }

    @Inject
    public EmployeeListBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.employees = this.employeeService.findAllEmployees();
    }

    public List<EmployeeServiceModel> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeServiceModel> employees) {
        this.employees = employees;
    }
}
