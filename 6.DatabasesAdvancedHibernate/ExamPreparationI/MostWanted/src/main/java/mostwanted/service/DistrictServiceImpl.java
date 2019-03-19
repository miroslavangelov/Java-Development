package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.domain.dtos.DistrictSeedDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Town;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static mostwanted.common.Constants.*;

@Service
public class DistrictServiceImpl implements DistrictService {
    private final static String DISTRICTS_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\districts.json";

    private final DistrictRepository districtRepository;
    private final TownRepository townRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository, TownRepository townRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.districtRepository = districtRepository;
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    public Boolean districtsAreImported() {
        return this.districtRepository.count() > 0;
    }

    public String readDistrictsJsonFile() throws IOException {
        return this.fileUtil.readFile(DISTRICTS_FILE_PATH);
    }

    public String importDistricts(String districtsFileContent) {
        StringBuilder result = new StringBuilder();
        DistrictSeedDto[] districtSeedDtos = this.gson.fromJson(districtsFileContent, DistrictSeedDto[].class);

        for (DistrictSeedDto districtSeedDto: districtSeedDtos) {
            District district = this.districtRepository.findByName(districtSeedDto.getName());

            if (district != null) {
                result.append(DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Town town = this.townRepository.findByName(districtSeedDto.getTownName());
            if (!this.validationUtil.isValid(districtSeedDto) || town == null) {
                result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            district = this.modelMapper.map(districtSeedDto, District.class);
            district.setTown(town);
            this.districtRepository.saveAndFlush(district);
            result.append(String.format(
                    SUCCESSFUL_IMPORT_MESSAGE,
                    district.getClass().getSimpleName(),
                    district.getName())
            ).append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}
