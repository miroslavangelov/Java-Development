package JavaOOPBasics.DefiningClasses.CarSalesman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int enginesCount = Integer.parseInt(reader.readLine());
        HashMap<String, Engine> engines = new HashMap<>();
        ArrayList<Car> cars = new ArrayList<>();

        for (int i = 0; i < enginesCount; i++) {
            String[] engineData = reader.readLine().split(" ");
            String engineModel = engineData[0];
            int power = Integer.parseInt(engineData[1]);
            int displacement;
            String efficiency;
            Engine engine;

            if (engineData.length == 2) {
                engine = new Engine(engineModel, power);
            } else if (engineData.length == 3 && isNumeric(engineData[2])) {
                displacement = Integer.parseInt(engineData[2]);
                engine = new Engine(engineModel, power, displacement);
            } else if (engineData.length == 3) {
                efficiency = engineData[2];
                engine = new Engine(engineModel, power, efficiency);
            } else {
                displacement = Integer.parseInt(engineData[2]);
                efficiency = engineData[3];
                engine = new Engine(engineModel, power, displacement, efficiency);
            }
            engines.putIfAbsent(engineModel, engine);
        }

        int carsCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < carsCount; i++) {
            String[] carData = reader.readLine().split(" ");
            String carModel = carData[0];
            String engineModel = carData[1];
            Engine engine = engines.get(engineModel);
            int weight;
            String color;
            Car car;

            if (carData.length == 2) {
                car = new Car(carModel, engine);
            } else if (carData.length == 3 && isNumeric(carData[2])) {
                weight = Integer.parseInt(carData[2]);
                car = new Car(carModel, engine, weight);
            } else if (carData.length == 3) {
                color = carData[2];
                car = new Car(carModel, engine, color);
            } else {
                weight = Integer.parseInt(carData[2]);
                color = carData[3];
                car = new Car(carModel, engine, weight, color);
            }
            cars.add(car);
        }

        for (int i = 0; i < cars.size(); i++) {
            Car currentCar = cars.get(i);
            System.out.println(String.format("%s:", currentCar.getModel()));
            System.out.println(String.format("  %s:", currentCar.getEngine().getModel()));
            System.out.println(String.format("    Power: %d", currentCar.getEngine().getPower()));
            System.out.println("    Displacement: " + (currentCar.getEngine().getDisplacement() != -1 ? currentCar.getEngine().getDisplacement() : "n/a"));
            System.out.println(String.format("    Efficiency: %s", currentCar.getEngine().getEfficiency()));
            System.out.println("  Weight: " + (currentCar.getWeight() != -1 ? currentCar.getWeight() : "n/a"));
            System.out.println(String.format("  Color: %s", currentCar.getColor()));
        }
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            int number = Integer.parseInt(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
