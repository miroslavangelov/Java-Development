package JavaOOPBasics.InterfacesAndAbstraction.BirthdayCelebrations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        List<IBirthdate> citizens = new ArrayList<>();

        while (!"End".equals(currentLine)) {
            String[] citizenData = currentLine.split(" ");
            String citizenType = citizenData[0];
            String citizenName = citizenData[1];
            if (citizenData.length == 3 && citizenType.equals("Pet")) {
                String birthdate = citizenData[2];
                IBirthdate pet = new Pet(citizenName, birthdate);
                citizens.add(pet);
            } else if (citizenData.length == 5) {
                int age = Integer.parseInt(citizenData[2]);
                String id = citizenData[3];
                String birthdate = citizenData[4];
                IBirthdate human = new Human(citizenName, age, id, birthdate);
                citizens.add(human);
            }

            currentLine = reader.readLine();
        }

        String year = reader.readLine();
        for (IBirthdate citizen: citizens) {
            if (citizen.isBirthdateInYear(year)) {
                System.out.println(citizen.getBirthdate());
            }
        }
    }
}
