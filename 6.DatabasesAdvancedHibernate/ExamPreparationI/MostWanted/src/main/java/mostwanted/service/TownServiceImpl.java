package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.domain.dtos.TownSeedDto;
import mostwanted.domain.entities.Town;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static mostwanted.common.Constants.*;

@Service
public class TownServiceImpl implements TownService {
    private final static String TOWNS_ENTRIES_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\towns.json";

    private final TownRepository townRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    public Boolean townsAreImported() {
        return this.townRepository.count() > 0;
    }

    public String readTownsJsonFile() throws IOException {
        return this.fileUtil.readFile(TOWNS_ENTRIES_FILE_PATH);
    }

    public String importTowns(String townsFileContent) {
        StringBuilder result = new StringBuilder();
        TownSeedDto[] townSeedDtos = this.gson.fromJson(townsFileContent, TownSeedDto[].class);

        for (TownSeedDto townSeedDto: townSeedDtos) {
            Town town = this.townRepository.findByName(townSeedDto.getName());

            if (town != null) {
                result.append(DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            if (!this.validationUtil.isValid(townSeedDto)) {
                result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            town = this.modelMapper.map(townSeedDto, Town.class);
            this.townRepository.saveAndFlush(town);
            result.append(String.format(
                    SUCCESSFUL_IMPORT_MESSAGE,
                    town.getClass().getSimpleName(),
                    town.getName())
            ).append(System.lineSeparator());
        }

        return result.toString().trim();
    }

    public String exportRacingTowns() {
        List<Object[]> towns = this.townRepository.exportTowns();
        StringBuilder result = new StringBuilder();

        towns.forEach(
                ob -> {
                    result.append(String.format("Name: %s", ob[0])).append(System.lineSeparator());
                    result.append(String.format("Racers: %s", ob[1])).append(System.lineSeparator());
                }
        );

        return result.toString().trim();
    }
}
