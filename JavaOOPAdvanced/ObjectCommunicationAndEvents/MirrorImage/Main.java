package JavaOOPAdvanced.ObjectCommunicationAndEvents.MirrorImage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] wizardData = reader.readLine().split("\\s+");
        String wizardName = wizardData[0];
        int wizardMagicalPower = Integer.parseInt(wizardData[1]);
        Wizard wizard = WizardFactory.create(wizardName, wizardMagicalPower);
        Repository.add(wizard);

        String currentLine = reader.readLine();

        while(!"END".equals(currentLine)) {
            String[] commandData = currentLine.split("\\s+");
            int wizardId = Integer.parseInt(commandData[0]);
            String commandName = commandData[1];

            switch (commandName) {
                case "FIREBALL":
                    Repository.get(wizardId).castFireball();
                    break;
                case "REFLECTION":
                    Repository.get(wizardId).castReflection();
                    break;
            }

            currentLine = reader.readLine();
        }
    }

}
