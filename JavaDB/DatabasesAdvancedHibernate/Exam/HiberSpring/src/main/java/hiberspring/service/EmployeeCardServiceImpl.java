package hiberspring.service;

import com.google.gson.Gson;
import hiberspring.domain.dtos.EmployeeCardImportDto;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static hiberspring.common.Constants.*;

@Service
public class EmployeeCardServiceImpl implements EmployeeCardService {
    private final static String EMPLOYEE_CARDS_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\employee-cards.json";

    private final EmployeeCardRepository employeeCardRepository;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    @Autowired
    public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository, ModelMapper modelMapper, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil) {
        this.employeeCardRepository = employeeCardRepository;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean employeeCardsAreImported() {
        return this.employeeCardRepository.count() > 0;
    }

    @Override
    public String readEmployeeCardsJsonFile() throws IOException {
        return this.fileUtil.readFile(EMPLOYEE_CARDS_FILE_PATH);
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) {
        StringBuilder result = new StringBuilder();
        EmployeeCardImportDto[] employeeCardImportDtos = this.gson.fromJson(employeeCardsFileContent, EmployeeCardImportDto[].class);

        for (EmployeeCardImportDto employeeCardImportDto: employeeCardImportDtos) {
            EmployeeCard employeeCard = this.employeeCardRepository.findByNumber(employeeCardImportDto.getNumber());

            if (employeeCard != null) {
                result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            employeeCard = this.modelMapper.map(employeeCardImportDto, EmployeeCard.class);

            if (!this.validationUtil.isValid(employeeCard)) {
                result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            this.employeeCardRepository.saveAndFlush(employeeCard);
            result.append(String.format(
                    SUCCESSFUL_IMPORT_MESSAGE,
                    employeeCard.getClass().getSimpleName(),
                    employeeCard.getNumber())
            ).append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}
