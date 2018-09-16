package JavaOOPBasics.ExamPreparationII.NeedForSpeed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        CarManager carManager = new CarManager();

        while (!"Cops Are Here".equals(currentLine)) {
            String[] data = currentLine.split(" ");
            String command = data[0];

            switch (command) {
                case "register":
                    int id = Integer.parseInt(data[1]);
                    String type = data[2];
                    String brand = data[3];
                    String model = data[4];
                    int year = Integer.parseInt(data[5]);
                    int horsepower = Integer.parseInt(data[6]);
                    int acceleration = Integer.parseInt(data[7]);
                    int suspension = Integer.parseInt(data[8]);
                    int durability = Integer.parseInt(data[9]);
                    carManager.register(id, type, brand, model, year, horsepower, acceleration, suspension, durability);
                    break;
                case "check":
                    id = Integer.parseInt(data[1]);
                    System.out.printf(carManager.check(id));
                    break;
                case "open":
                    id = Integer.parseInt(data[1]);
                    type = data[2];
                    int length = Integer.parseInt(data[3]);
                    String route = data[4];
                    int prizePool = Integer.parseInt(data[5]);
                    carManager.open(id, type, length, route, prizePool);
                    break;
                case "participate":
                    int carId = Integer.parseInt(data[1]);
                    int raceId = Integer.parseInt(data[2]);
                    carManager.participate(carId, raceId);
                    break;
                case "start":
                    raceId = Integer.parseInt(data[1]);
                    System.out.printf(carManager.start(raceId));
                    break;
                case "park":
                    raceId = Integer.parseInt(data[1]);
                    carManager.park(raceId);
                    break;
                case "unpark":
                    carId = Integer.parseInt(data[1]);
                    carManager.unpark(carId);
                    break;
                case "tune":
                    int tuneIndex = Integer.parseInt(data[1]);
                    String addOn = data[2];
                    carManager.tune(tuneIndex, addOn);
                    break;
            }

            currentLine = reader.readLine();
        }
    }
}
