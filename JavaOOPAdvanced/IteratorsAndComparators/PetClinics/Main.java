package JavaOOPAdvanced.IteratorsAndComparators.PetClinics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int commandsCount = Integer.parseInt(reader.readLine());
        Map<String, Pet> pets = new HashMap<>();
        Map<String, Clinic> clinics = new HashMap<>();

        for (int i = 0; i < commandsCount; i++) {
            String[] commandData = reader.readLine().split(" ");
            String command = commandData[0];

            switch (command) {
                case "Create":
                    String type = commandData[1];
                    if (type.equals("Pet")) {
                        String petName = commandData[2];
                        int petAge = Integer.parseInt(commandData[3]);
                        String petKind = commandData[4];
                        Pet pet = new Pet(petName, petAge, petKind);

                        pets.put(petName, pet);
                    } else if (type.equals("Clinic")) {
                        try {
                            String clinicName = commandData[2];
                            int clinicRooms = Integer.parseInt(commandData[3]);
                            Clinic clinic = new Clinic(clinicName, clinicRooms);

                            clinics.put(clinicName, clinic);
                        } catch (IllegalArgumentException exception) {
                            System.out.println(exception.getMessage());
                        }
                    }
                    break;
                case "HasEmptyRooms":
                    String clinicName = commandData[1];
                    Clinic clinic = clinics.get(clinicName);
                    System.out.println(clinic.hasEmptyRooms());
                    break;
                case "Add":
                    String petName = commandData[1];
                    clinicName = commandData[2];
                    Pet pet = pets.get(petName);

                    clinic = clinics.get(clinicName);
                    System.out.println(clinic.addPet(pet));
                    break;
                case "Release":
                    clinicName = commandData[1];
                    clinic = clinics.get(clinicName);

                    System.out.println(clinic.releasePet());
                    break;
                case "Print":
                    clinicName = commandData[1];
                    clinic = clinics.get(clinicName);

                    if (commandData.length == 3) {
                        int index = Integer.parseInt(commandData[2]);
                        clinic.printRoom(index);
                    } else if (commandData.length == 2) {
                        clinic.printAllRooms();
                    }
                    break;
            }
        }
    }
}
