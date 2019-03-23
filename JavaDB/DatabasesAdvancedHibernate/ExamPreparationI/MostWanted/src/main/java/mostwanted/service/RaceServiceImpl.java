package mostwanted.service;

import mostwanted.domain.dtos.EntryDto;
import mostwanted.domain.dtos.RaceSeedDto;
import mostwanted.domain.dtos.RaceSeedRootDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Race;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RaceRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static mostwanted.common.Constants.*;

@Service
public class RaceServiceImpl implements RaceService {
    private final static String RACES_ENTRIES_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\races.xml";

    private final RaceRepository raceRepository;
    private final DistrictRepository districtRepository;
    private final RaceEntryRepository raceEntryRepository;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository, DistrictRepository districtRepository, RaceEntryRepository raceEntryRepository, FileUtil fileUtil, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.raceRepository = raceRepository;
        this.districtRepository = districtRepository;
        this.raceEntryRepository = raceEntryRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    public Boolean racesAreImported() {
        return this.raceRepository.count() > 0;
    }

    public String readRacesXmlFile() throws IOException {
        return this.fileUtil.readFile(RACES_ENTRIES_FILE_PATH);
    }

    public String importRaces() throws JAXBException {
        StringBuilder result = new StringBuilder();
        RaceSeedRootDto raceSeedRootDto = this.xmlParser
                .parseXml(RaceSeedRootDto.class, RACES_ENTRIES_FILE_PATH);

        for (RaceSeedDto raceSeedDto: raceSeedRootDto.getRaceSeedDtos()) {
            if (!this.validationUtil.isValid(raceSeedDto)) {
                result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            District district = this.districtRepository.findByName(raceSeedDto.getDistrictName());
            Race race = this.modelMapper.map(raceSeedDto, Race.class);
            race.setDistrict(district);
            List<RaceEntry> raceEntries = new ArrayList<>();

            for (EntryDto entryDto: raceSeedDto.getEntryRootDto().getEntryDtos()) {
                RaceEntry raceEntry = this.raceEntryRepository.findById(entryDto.getId()).orElse(null);

                if (raceEntry == null){
                    result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                    continue;
                }

                raceEntry.setRace(race);
                raceEntries.add(raceEntry);
            }

            race.setEntries(raceEntries);
            this.raceRepository.saveAndFlush(race);
            result.append(String.format(
                    SUCCESSFUL_IMPORT_MESSAGE,
                    race.getClass().getSimpleName(),
                    race.getId())
            ).append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}
