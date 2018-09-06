package JavaOOPBasics.InterfacesAndAbstraction.BorderControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        List<Citizen> citizens = new ArrayList<>();

        while (!"End".equals(currentLine)) {
            String[] citizenData = currentLine.split(" ");
            String citizenName = citizenData[0];
            if (citizenData.length == 2) {
                String robotId = citizenData[1];
                Citizen robot = new Robot(citizenName, robotId);
                citizens.add(robot);
            } else if (citizenData.length == 3) {
                int age = Integer.parseInt(citizenData[1]);
                String id = citizenData[2];
                Citizen human = new Human(citizenName, age, id);
                citizens.add(human);
            }

            currentLine = reader.readLine();
        }

        String fakeId = reader.readLine();
        for (Citizen citizen: citizens) {
            if (citizen.isFakedId(fakeId)) {
                System.out.println(citizen.getId());
            }
        }
    }
}
