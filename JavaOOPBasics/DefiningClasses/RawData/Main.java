package JavaOOPBasics.DefiningClasses.RawData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int carsCount = Integer.parseInt(reader.readLine());
        ArrayList<Car> cars = new ArrayList<>();

        for (int i = 0; i < carsCount; i++) {
            String[] carData = reader.readLine().split(" ");
            String carModel = carData[0];
            int engineSpeed = Integer.parseInt(carData[1]);
            int enginePower = Integer.parseInt(carData[2]);
            int cargoWeight = Integer.parseInt(carData[3]);
            String cargoType = carData[4];
            double tire1Pressure = Double.parseDouble(carData[5]);
            int tire1Age = Integer.parseInt(carData[6]);
            double tire2Pressure = Double.parseDouble(carData[7]);
            int tire2Age = Integer.parseInt(carData[8]);
            double tire3Pressure = Double.parseDouble(carData[9]);
            int tire3Age = Integer.parseInt(carData[10]);
            double tire4Pressure = Double.parseDouble(carData[11]);
            int tire4Age = Integer.parseInt(carData[12]);

            Engine engine = new Engine(engineSpeed, enginePower);
            Cargo cargo = new Cargo(cargoWeight, cargoType);
            Tire[] tires = new Tire[4];
            tires[0] = new Tire(tire1Pressure, tire1Age);
            tires[1] = new Tire(tire2Pressure, tire2Age);
            tires[2] = new Tire(tire3Pressure, tire3Age);
            tires[3] = new Tire(tire4Pressure, tire4Age);
            Car car = new Car(carModel, engine, cargo, tires);
            cars.add(car);
        }

        String command = reader.readLine();

        if (command.equals("fragile")) {
            for (int i = 0; i < cars.size(); i++) {
                Car currentCar = cars.get(i);
                if (currentCar.getCargo().getType().equals("fragile")) {
                    for (int j = 0; j < currentCar.getTires().length; j++) {
                        Tire currentTire = currentCar.getTires()[j];

                        if (currentTire.getPressure() < 1) {
                            System.out.println(currentCar.getModel());
                            break;
                        }
                    }
                }
            }
        } else if (command.equals("flamable")) {
            for (int i = 0; i < cars.size(); i++) {
                Car currentCar = cars.get(i);
                if (currentCar.getCargo().getType().equals("flamable") && currentCar.getEngine().getPower() > 250) {
                    System.out.println(currentCar.getModel());
                }
            }
        }
    }
}
