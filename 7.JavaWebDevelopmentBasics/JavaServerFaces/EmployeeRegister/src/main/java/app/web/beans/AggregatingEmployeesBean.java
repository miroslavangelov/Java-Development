package app.web.beans;

import app.domain.models.service.EmployeeServiceModel;
import app.services.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Named
@RequestScoped
public class AggregatingEmployeesBean {
    private EmployeeService employeeService;

    public AggregatingEmployeesBean() {

    }

    @Inject
    public AggregatingEmployeesBean(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public BigDecimal getSalaryTotalSum() {
        BigDecimal[] totalSum = {new BigDecimal(0)};
        this.employeeService.findAllEmployees().forEach(employee -> totalSum[0] = totalSum[0].add(employee.getSalary()));
        return totalSum[0];
    }

    public BigDecimal getAverageSalary() {
        BigDecimal[] averageSalary = {new BigDecimal(0)};
        List<EmployeeServiceModel> employees = this.employeeService.findAllEmployees();
        employees.forEach(employee -> averageSalary[0] = averageSalary[0].add(employee.getSalary()));
        return averageSalary[0].divide(new BigDecimal(employees.size()), 2, RoundingMode.HALF_UP);
    }
}
