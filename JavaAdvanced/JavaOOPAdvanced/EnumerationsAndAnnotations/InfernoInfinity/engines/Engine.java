package JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity.engines;

import JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity.annotations.CustomAnnotation;
import JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity.models.Gem;
import JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity.models.Weapon;
import JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity.models.WeaponType;
import JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity.factories.GemFactory;
import JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity.factories.WeaponFactory;
import JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity.factories.WeaponTypeFactory;
import JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity.io.ConsoleInputReader;
import JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity.io.ConsoleOutputWriter;

import java.util.HashMap;
import java.util.Map;

public class Engine {
    private static final String TERMINATE_COMMAND = "END";

    private ConsoleInputReader inputReader;
    private ConsoleOutputWriter outputWriter;
    private Map<String, Weapon> weapons;
    private CustomAnnotation customAnnotation;

    public Engine(ConsoleInputReader inputReader, ConsoleOutputWriter outputWriter) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.weapons = new HashMap<>();
        this.customAnnotation = Weapon.class.getAnnotation(CustomAnnotation.class);
    }

    public void run() {
        String currentLine = inputReader.readLine();

        while (!TERMINATE_COMMAND.equals(currentLine)) {
            String[] data = currentLine.split(";");
            this.dispatchCommand(data);
            currentLine = inputReader.readLine();
        }
    }

    private void dispatchCommand(String[] data) {
        String command = data[0];
        switch (command) {
            case "Create":
                WeaponType weaponType = WeaponTypeFactory.create(data[1]);
                String weaponName = data[2];
                Weapon weapon = WeaponFactory.create(weaponName, weaponType);

                weapons.put(weaponName, weapon);
                break;
            case "Add":
                weaponName = data[1];
                int socketIndex = Integer.parseInt(data[2]);
                Gem gem = GemFactory.create(data[3]);
                weapon = weapons.get(weaponName);

                weapon.addGem(socketIndex, gem);
                break;
            case "Remove":
                weaponName = data[1];
                socketIndex = Integer.parseInt(data[2]);
                weapon = weapons.get(weaponName);

                weapon.removeGem(socketIndex);
                break;
            case "Print":
                weaponName = data[1];
                weapon = weapons.get(weaponName);

                outputWriter.writeLine(weapon.toString());
                break;
            case "Compare":
                weaponName = data[1];
                String secondWeaponName = data[2];
                weapon = weapons.get(weaponName);
                Weapon secondWeapon = weapons.get(secondWeaponName);

                if (weapon.compareTo(secondWeapon) > 0) {
                    outputWriter.writeLine(weapon.printGreaterWeapon());
                } else {
                    outputWriter.writeLine(secondWeapon.printGreaterWeapon());
                }
                break;
            case "Author":
                outputWriter.writeLine(String.format("Author: %s", customAnnotation.author()));
                break;
            case "Revision":
                outputWriter.writeLine(String.format("Revision: %d", customAnnotation.revision()));
                break;
            case "Description":
                outputWriter.writeLine(String.format("Class description: %s", customAnnotation.description()));
                break;
            case "Reviewers":
                outputWriter.writeLine(String.format("Reviewers: %s", String.join(", ", customAnnotation.reviewers())));
                break;
        }
    }
}