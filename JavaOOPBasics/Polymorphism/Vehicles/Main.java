package JavaOOPBasics.Polymorphism.Vehicles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] carData = reader.readLine().split(" ");
        double carFuel = Double.parseDouble(carData[1]);
        double carConsumption = Double.parseDouble(carData[2]);
        Vehicle car = new Car(carFuel, carConsumption);
        String[] truckData = reader.readLine().split(" ");
        double truckFuel = Double.parseDouble(truckData[1]);
        double truckConsumption = Double.parseDouble(truckData[2]);
        Vehicle truck = new Truck(truckFuel, truckConsumption);
        int commandsCount = Integer.parseInt(reader.readLine());
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < commandsCount; i++) {
            String[] commandData = reader.readLine().split(" ");
            Vehicle currentVehicle = commandData[1].equals("Car") ? car : truck;

            switch (commandData[0]) {
                case "Drive":
                    double distance = Double.parseDouble(commandData[2]);
                    result.append(currentVehicle.driveDistance(distance));break;
                case "Refuel":
                    double fuel = Double.parseDouble(commandData[2]);
                    currentVehicle.refuel(fuel);break;
            }
        }
        System.out.print(result.toString());
        System.out.println(String.format("Car: %.2f", car.getFuel()));
        System.out.println(String.format("Truck: %.2f", truck.getFuel()));
    }
}
