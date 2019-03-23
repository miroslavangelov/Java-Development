package JavaOOPBasics.Exam.TheExpanse;

import JavaOOPBasics.Exam.TheExpanse.Colonists.*;
import JavaOOPBasics.Exam.TheExpanse.Colony.Colony;
import JavaOOPBasics.Exam.TheExpanse.Family.Family;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        String[] colonyData = currentLine.split(" ");
        int maxFamilyCount = Integer.parseInt(colonyData[0]);
        int maxFamilyCapacity = Integer.parseInt(colonyData[1]);
        Colony colony = new Colony(maxFamilyCount, maxFamilyCapacity);

        while(!"end".equals(currentLine)) {
            String[] data = currentLine.split(" ");
            String command = data[0];

            switch (command) {
                case "insert":
                    try {
                        String className = data[1];
                        String colonistId = data[2];
                        String familyId = data[3];
                        int talent = Integer.parseInt(data[4]);
                        int age = Integer.parseInt(data[5]);
                        switch (className) {
                            case "SoftwareEngineer":
                                SoftwareEngineer softwareEngineer = new SoftwareEngineer(colonistId, familyId, talent, age);
                                colony.addColonist(softwareEngineer);
                                break;
                            case "HardwareEngineer":
                                HardwareEngineer hardwareEngineer = new HardwareEngineer(colonistId, familyId, talent, age);
                                colony.addColonist(hardwareEngineer);
                                break;
                            case "Soldier":
                                Soldier soldier = new Soldier(colonistId, familyId, talent, age);
                                colony.addColonist(soldier);
                                break;
                            case "GeneralPractitioner":
                                String sign = data[6];
                                GeneralPractitioner generalPractitioner = new GeneralPractitioner(colonistId, familyId, talent, age, sign);
                                colony.addColonist(generalPractitioner);
                                break;
                            case "Surgeon":
                                sign = data[6];
                                Surgeon surgeon = new Surgeon(colonistId, familyId, talent, age, sign);
                                colony.addColonist(surgeon);
                                break;
                        }
                    } catch (IllegalArgumentException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case "remove":
                    String modificator = data[1];
                    String familyId = data[2];
                    switch (modificator) {
                        case "family":
                            colony.removeFamily(familyId);
                            break;
                        case "colonist":
                            String colonistId = data[3];
                            colony.removeColonist(familyId, colonistId);
                            break;
                    }
                    break;
                case "grow":
                    int years = Integer.parseInt(data[1]);
                    colony.grow(years);
                    break;
                case "potential":
                    System.out.println(String.format("potential: %d", colony.getPotential()));
                    break;
                case "capacity":
                    System.out.print(colony.getCapacity());
                    break;
                case "family":
                    familyId = data[1];
                    Family family = colony.getFamilyById(familyId);
                    if (family != null) {
                        System.out.print(family.toString());
                    } else {
                        System.out.println("family does not exist");
                    }
            }

            currentLine = reader.readLine();
        }
    }
}
