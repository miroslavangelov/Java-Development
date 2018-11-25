package JavaOOPAdvanced.ObjectCommunicationAndEvents.KingsGambit;

import JavaOOPAdvanced.ObjectCommunicationAndEvents.KingsGambit.models.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, BaseUnit> soldiers = new HashMap<>();
        Subject subject = new Subject();
        String kingName = reader.readLine();
        King king = new King(kingName, subject);
        subject.attach(king);

        String[] royalGuardsData = reader.readLine().split("\\s+");
        for (String royalGuardName : royalGuardsData) {
            BaseUnit royalGuard = new RoyalGuard(royalGuardName);
            soldiers.putIfAbsent(royalGuardName, royalGuard);
            subject.attach(royalGuard);
        }

        String[] footmenData = reader.readLine().split("\\s+");
        for (String footmanName : footmenData) {
            BaseUnit footman = new Footman(footmanName);
            soldiers.putIfAbsent(footmanName, footman);
            subject.attach(footman);
        }

        String currentLine = reader.readLine();
        while (!"End".equals(currentLine)) {
            String[] commandData = currentLine.split("\\s+");
            String commandName = commandData[0];

            switch (commandName) {
                case "Attack":
                    king.respondToAttack();
                    break;
                case "Kill":
                    String soldierName = commandData[1];
                    BaseUnit soldier = soldiers.get(soldierName);
                    soldiers.remove(soldierName);
                    subject.remove(soldier);
                    break;
            }

            currentLine = reader.readLine();
        }
    }
}
