package hiberspring.service;

import hiberspring.domain.dtos.EmployeeImportDto;
import hiberspring.domain.dtos.EmployeeImportRootDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.repository.EmployeeRepository;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static hiberspring.common.Constants.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final static String EMPLOYEES_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\employees.xml";

    private final EmployeeRepository employeeRepository;
    private final EmployeeCardRepository employeeCardRepository;
    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeCardRepository employeeCardRepository, BranchRepository branchRepository, ModelMapper modelMapper, FileUtil fileUtil, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.employeeRepository = employeeRepository;
        this.employeeCardRepository = employeeCardRepository;
        this.branchRepository = branchRepository;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return this.fileUtil.readFile(EMPLOYEES_FILE_PATH);
    }

    @Override
    public String importEmployees() throws JAXBException, FileNotFoundException {
        StringBuilder result = new StringBuilder();
        EmployeeImportRootDto employeeImportRootDto = this.xmlParser
                .parseXml(EmployeeImportRootDto.class, EMPLOYEES_FILE_PATH);

        for (EmployeeImportDto employeeImportDto: employeeImportRootDto.getEmployeeImportDtos()) {
            EmployeeCard employeeCard = this.employeeCardRepository.findByNumber(employeeImportDto.getCard());
            Branch branch = this.branchRepository.findByName(employeeImportDto.getBranch());

            if (employeeCard == null || branch == null) {
                result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Employee employee = this.employeeRepository.findByCard(employeeCard);

            if (employee != null) {
                result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            employee = this.modelMapper.map(employeeImportDto, Employee.class);
            employee.setBranch(branch);
            employee.setCard(employeeCard);

            if (!this.validationUtil.isValid(employee)) {
                result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            this.employeeRepository.saveAndFlush(employee);
            result.append(String.format(
                    SUCCESSFUL_IMPORT_MESSAGE,
                    employee.getClass().getSimpleName(),
                    employee.getFirstName() + " " + employee.getLastName())
            ).append(System.lineSeparator());
        }

        return result.toString().trim();
    }

    @Override
    public String exportProductiveEmployees() {
        StringBuilder result = new StringBuilder();
        List<Employee> employees = this.employeeRepository.exportEmployees();

        for (Employee employee: employees) {
            result.append(String.format("Name: %s %s", employee.getFirstName(), employee.getLastName()))
                    .append(System.lineSeparator())
                    .append(String.format("Position: %s", employee.getPosition()))
                    .append(System.lineSeparator())
                    .append(String.format("Card Number: %s", employee.getCard().getNumber()))
                    .append(System.lineSeparator())
                    .append("-------------------------")
                    .append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}
