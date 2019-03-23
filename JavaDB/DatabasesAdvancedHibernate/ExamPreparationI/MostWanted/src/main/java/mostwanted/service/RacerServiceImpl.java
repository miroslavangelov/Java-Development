package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.domain.dtos.RacerSeedDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import mostwanted.domain.entities.Town;
import mostwanted.repository.RacerRepository;
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
public class RacerServiceImpl implements RacerService {
    private final static String RACERS_ENTRIES_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\racers.json";

    private final RacerRepository racerRepository;
    private final TownRepository townRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public RacerServiceImpl(RacerRepository racerRepository, TownRepository townRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.racerRepository = racerRepository;
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    public Boolean racersAreImported() {
        return this.racerRepository.count() > 0;
    }

    public String readRacersJsonFile() throws IOException {
        return this.fileUtil.readFile(RACERS_ENTRIES_FILE_PATH);
    }

    public String importRacers(String racersFileContent) {
        StringBuilder result = new StringBuilder();
        RacerSeedDto[] racerSeedDtos = this.gson.fromJson(racersFileContent, RacerSeedDto[].class);

        for (RacerSeedDto racerSeedDto : racerSeedDtos) {
            Racer racer = this.racerRepository.findByName(racerSeedDto.getName());

            if (racer != null) {
                result.append(DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            if (!this.validationUtil.isValid(racerSeedDto)) {
                result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Town town = this.townRepository.findByName(racerSeedDto.getHomeTown());
            racer = this.modelMapper.map(racerSeedDto, Racer.class);
            racer.setHomeTown(town);
            this.racerRepository.saveAndFlush(racer);
            result.append(String.format(
                    SUCCESSFUL_IMPORT_MESSAGE,
                    racer.getClass().getSimpleName(),
                    racer.getName())
            ).append(System.lineSeparator());
        }

        return result.toString().trim();
    }

    public String exportRacingCars() {
        List<Racer> racers = this.racerRepository.exportRacers();
        StringBuilder result = new StringBuilder();

        racers.forEach(racer -> {
            result.append(String.format("Name: %s", racer.getName())).append(System.lineSeparator());
            if (racer.getAge() != null) {
                result.append(String.format("Age: %d", racer.getAge())).append(System.lineSeparator());
            }
            result.append("Cars:").append(System.lineSeparator());

            for (Car car: racer.getCars()) {
                result.append(String.format(
                        " %s %s %d",
                        car.getBrand(),
                        car.getModel(),
                        car.getYearOfProduction())
                ).append(System.lineSeparator());
            }
        });

        return result.toString();
    }
}
