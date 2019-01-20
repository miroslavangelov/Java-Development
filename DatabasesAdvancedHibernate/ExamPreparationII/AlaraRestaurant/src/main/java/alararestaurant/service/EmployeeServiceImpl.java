package alararestaurant.service;

import alararestaurant.domain.dtos.EmployeeImportDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Position;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.PositionRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static alararestaurant.constants.Constants.INCORRECT_DATA_MESSAGE;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final static String EMPLOYEES_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\employees.json";

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PositionRepository positionRepository, ModelMapper modelMapper, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        return this.fileUtil.readFile(EMPLOYEES_FILE_PATH);
    }

    @Override
    public String importEmployees(String employees) {
        StringBuilder result = new StringBuilder();
        EmployeeImportDto[] employeeImportDtos = this.gson.fromJson(employees, EmployeeImportDto[].class);

        for (EmployeeImportDto employeeImportDto: employeeImportDtos) {
            Employee employee = this.modelMapper.map(employeeImportDto, Employee.class);

            if (!this.validationUtil.isValid(employee)) {
                result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Position position = this.positionRepository.findByName(employeeImportDto.getPosition());

            if (position == null) {
                position = new Position();
                position.setName(employeeImportDto.getPosition());

                if (!this.validationUtil.isValid(position)) {
                    result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                    continue;
                }

                this.positionRepository.saveAndFlush(position);
            }

            employee.setPosition(position);

            this.employeeRepository.saveAndFlush(employee);

            result.append(String.format("Record %s successfully imported.", employee.getName()))
                    .append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}
