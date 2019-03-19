package JavaOOPBasics.Polymorphism.VehiclesExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Vehicle> vehicles = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            String[] vehicleData = reader.readLine().split(" ");
            double fuel = Double.parseDouble(vehicleData[1]);
            double consumption = Double.parseDouble(vehicleData[2]);
            double tankCapacity = Double.parseDouble(vehicleData[3]);

            switch (vehicleData[0].toLowerCase()) {
                case "car":
                    Vehicle car = new Car(fuel, consumption, tankCapacity);
                    vehicles.putIfAbsent(vehicleData[0].toLowerCase(), car);break;
                case "truck":
                    Vehicle truck = new Truck(fuel, consumption, tankCapacity);
                    vehicles.putIfAbsent(vehicleData[0].toLowerCase(), truck);break;
                case "bus":
                    Vehicle bus = new Bus(fuel, consumption, tankCapacity);
                    vehicles.putIfAbsent(vehicleData[0].toLowerCase(), bus);break;
            }
        }
        int commandsCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < commandsCount; i++) {
            String[] commandData = reader.readLine().split(" ");
            Vehicle currentVehicle = vehicles.get(commandData[1].toLowerCase());
            switch (commandData[0]) {
                case "Drive":
                    double distance = Double.parseDouble(commandData[2]);
                    if (commandData[1].equals("Bus")) {
                        currentVehicle.driveDistance(distance, 1.4);
                    } else {
                        currentVehicle.driveDistance(distance, 0);
                    }
                    break;
                case "Refuel":
                    double fuel = Double.parseDouble(commandData[2]);
                    currentVehicle.refuel(fuel);break;
                case "DriveEmpty":
                    distance = Double.parseDouble(commandData[2]);
                    currentVehicle.driveDistance(distance, 0);break;
            }
        }
        System.out.println(String.format("Car: %.2f", vehicles.get("car").getFuel()));
        System.out.println(String.format("Truck: %.2f", vehicles.get("truck").getFuel()));
        System.out.println(String.format("Bus: %.2f", vehicles.get("bus").getFuel()));
    }
}
