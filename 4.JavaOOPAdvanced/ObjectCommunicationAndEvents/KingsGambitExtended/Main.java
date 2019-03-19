package JavaOOPAdvanced.ObjectCommunicationAndEvents.KingsGambitExtended;

import JavaOOPAdvanced.ObjectCommunicationAndEvents.KingsGambitExtended.events.KillEvent;
import JavaOOPAdvanced.ObjectCommunicationAndEvents.KingsGambitExtended.models.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Soldier> soldiers = new LinkedHashMap<>();
        Subject subject = new Subject();
        String kingName = reader.readLine();
        KillEvent killEvent = new KillEvent();
        King king = new King(kingName, subject);
        subject.attach(king);

        String[] royalGuardsData = reader.readLine().split("\\s+");
        for (String royalGuardName : royalGuardsData) {
            Soldier royalGuard = new RoyalGuard(royalGuardName);
            soldiers.putIfAbsent(royalGuardName, royalGuard);
            subject.attach(royalGuard);
        }

        String[] footmenData = reader.readLine().split("\\s+");
        for (String footmanName : footmenData) {
            Soldier footman = new Footman(footmanName);
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
                    Soldier soldier = soldiers.get(soldierName);
                    killEvent.executeEvent(soldier, subject);
                    break;
            }

            currentLine = reader.readLine();
        }
    }
}
