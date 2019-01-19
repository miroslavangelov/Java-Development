package mostwanted.service;

import mostwanted.domain.dtos.RaceEntrySeedDto;
import mostwanted.domain.dtos.RaceEntrySeedRootDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static mostwanted.common.Constants.*;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {
    private final static String RACE_ENTRIES_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\race-entries.xml";

    private final RaceEntryRepository raceEntryRepository;
    private final CarRepository carRepository;
    private final RacerRepository racerRepository;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository, CarRepository carRepository, RacerRepository racerRepository, FileUtil fileUtil, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.raceEntryRepository = raceEntryRepository;
        this.carRepository = carRepository;
        this.racerRepository = racerRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    public Boolean raceEntriesAreImported() {
        return this.raceEntryRepository.count() > 0;
    }

    public String readRaceEntriesXmlFile() throws IOException {
        return this.fileUtil.readFile(RACE_ENTRIES_FILE_PATH);
    }

    public String importRaceEntries() throws JAXBException {
        StringBuilder result = new StringBuilder();
        RaceEntrySeedRootDto raceEntrySeedRootDto = this.xmlParser
                .parseXml(RaceEntrySeedRootDto.class, RACE_ENTRIES_FILE_PATH);

        for (RaceEntrySeedDto raceEntrySeedDto: raceEntrySeedRootDto.getRaceEntrySeedDtos()) {
            if (!this.validationUtil.isValid(raceEntrySeedDto)) {
                result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Car car = this.carRepository.findById(raceEntrySeedDto.getCarId()).orElse(null);
            Racer racer = this.racerRepository.findByName(raceEntrySeedDto.getRacerName());
            RaceEntry raceEntry = this.modelMapper.map(raceEntrySeedDto, RaceEntry.class);
            raceEntry.setCar(car);
            raceEntry.setRacer(racer);
            raceEntry.setRace(null);

            this.raceEntryRepository.saveAndFlush(raceEntry);
            result.append(String.format(
                    SUCCESSFUL_IMPORT_MESSAGE,
                    raceEntry.getClass().getSimpleName(),
                    raceEntry.getId())
            ).append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}
