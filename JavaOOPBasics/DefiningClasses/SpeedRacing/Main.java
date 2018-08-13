package JavaOOPBasics.DefiningClasses.SpeedRacing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int carsCount = Integer.parseInt(reader.readLine());
        LinkedHashMap<String, Car> cars = new LinkedHashMap<>();

        for (int i = 0; i < carsCount; i++) {
            String[] carData = reader.readLine().split(" ");
            String carModel = carData[0];
            double fuelAmount = Double.parseDouble(carData[1]);
            double fuelCostFor1km = Double.parseDouble(carData[2]);
            Car car = new Car(carModel, fuelAmount, fuelCostFor1km);
            cars.putIfAbsent(carModel, car);
        }

        String currentLine = reader.readLine();
        while (!"End".equals(currentLine)) {
            String[] commandData = currentLine.split(" ");
            String carModel = commandData[1];
            int distance = Integer.parseInt(commandData[2]);
            Car currentCar = cars.get(carModel);
            currentCar.driveCar(distance);
            currentLine = reader.readLine();
        }

        cars.entrySet().stream()
                .forEach(car -> System.out.println(String.format("%s %.2f %d", car.getValue().getModel(), car.getValue().getFuelAmount(), car.getValue().getTraveledDistance())));
    }
}
