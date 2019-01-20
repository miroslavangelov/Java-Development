package hiberspring.service;

import com.google.gson.Gson;
import hiberspring.domain.dtos.TownImportDto;
import hiberspring.domain.entities.Town;
import hiberspring.repository.TownRepository;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static hiberspring.common.Constants.*;

@Service
public class TownServiceImpl implements TownService {
    private final static String TOWNS_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\towns.json";

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean townsAreImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return this.fileUtil.readFile(TOWNS_FILE_PATH);
    }

    @Override
    public String importTowns(String townsFileContent) {
        StringBuilder result = new StringBuilder();
        TownImportDto[] townImportDtos = this.gson.fromJson(townsFileContent, TownImportDto[].class);

        for (TownImportDto townImportDto: townImportDtos) {
            Town town = this.modelMapper.map(townImportDto, Town.class);

            if (!this.validationUtil.isValid(town)) {
                result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            this.townRepository.saveAndFlush(town);
            result.append(String.format(
                    SUCCESSFUL_IMPORT_MESSAGE,
                    town.getClass().getSimpleName(),
                    town.getName())
            ).append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}
