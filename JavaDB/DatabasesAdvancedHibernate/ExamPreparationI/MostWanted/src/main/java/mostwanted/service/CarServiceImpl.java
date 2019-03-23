package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.domain.dtos.CarSeedDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static mostwanted.common.Constants.*;

@Service
public class CarServiceImpl implements CarService {
    private final static String CARS_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\cars.json";

    private final CarRepository carRepository;
    private final RacerRepository racerRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, RacerRepository racerRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.racerRepository = racerRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    public Boolean carsAreImported() {
        return this.carRepository.count() > 0;
    }

    public String readCarsJsonFile() throws IOException {
        return this.fileUtil.readFile(CARS_FILE_PATH);
    }

    public String importCars(String carsFileContent) {
        StringBuilder result = new StringBuilder();
        CarSeedDto[] carSeedDtos = this.gson.fromJson(carsFileContent, CarSeedDto[].class);

        for (CarSeedDto carSeedDto : carSeedDtos) {
            if (!this.validationUtil.isValid(carSeedDto)) {
                result.append(INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Racer racer = this.racerRepository.findByName(carSeedDto.getRacerName());
            Car car = this.modelMapper.map(carSeedDto, Car.class);
            car.setRacer(racer);
            this.carRepository.saveAndFlush(car);
            result.append(String.format(
                    SUCCESSFUL_IMPORT_MESSAGE,
                    car.getClass().getSimpleName(),
                    String.format(
                        "%s %s @ %d",
                        car.getBrand(),
                        car.getModel(),
                        car.getYearOfProduction()
                    )
            )).append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}
