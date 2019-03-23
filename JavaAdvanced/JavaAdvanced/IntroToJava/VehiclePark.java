package JavaAdvanced.IntroToJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class VehiclePark {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<String> vehicles = new LinkedList<>();
        vehicles.addAll(Arrays.asList(reader.readLine().split(" ")));
        String request = reader.readLine();
        int vehiclesSold = 0;

        while (!request.equals("End of customers!")) {
            String[] input = request.split(" ");
            String vehicleType = Character.toString(input[0].charAt(0)).toLowerCase();
            String numberOfSeats = input[2];
            String currentVehicle = vehicleType + numberOfSeats;
            boolean isSold = false;

            for (String vehicle: vehicles) {
                if (vehicle.equals(currentVehicle)) {
                    int vehiclePrice = (int)vehicle.charAt(0) * Integer.parseInt(numberOfSeats);
                    System.out.println(String.format("Yes, sold for %d$", vehiclePrice));
                    vehicles.remove(vehicle);
                    isSold = true;
                    vehiclesSold += 1;
                    break;
                }
            }

            if (!isSold) {
                System.out.println("No");
            }

            request = reader.readLine();
        }
        System.out.println(String.format("Vehicles left: %s", vehicles.toString()
                .replace("[", "")
                .replace("]", "")
                .trim()));
        System.out.println(String.format("Vehicles sold: %d", vehiclesSold));
    }
}
