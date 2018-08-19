package JavaOOPBasics.Encapsulation.FootballTeamGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        HashMap<String, Team> teams = new HashMap<>();

        while (!"END".equals(currentLine)) {
            String[] commandData = currentLine.split(";");
            String command = commandData[0];

            if (command.equals("Team")) {
                try {
                    if (commandData.length > 1) {
                        String teamName = commandData[1];
                        Team team = new Team(teamName);
                        teams.putIfAbsent(teamName, team);
                    } else {
                        throw new IllegalArgumentException("A name should not be empty.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("Add")) {
                try {
                    String teamName = commandData[1];
                    if (teams.get(teamName) == null) {
                        throw new IllegalArgumentException(String.format("Team %s does not exist.", teamName));
                    } else {
                        String playerName = commandData[2];
                        int endurance = Integer.parseInt(commandData[3]);
                        int sprint = Integer.parseInt(commandData[4]);
                        int dribble = Integer.parseInt(commandData[5]);
                        int passing = Integer.parseInt(commandData[6]);
                        int shooting = Integer.parseInt(commandData[7]);
                        Player player = new Player(playerName, endurance, sprint, dribble, passing, shooting);
                        teams.get(teamName).addPlayer(player);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("Remove")) {
                try {
                    String teamName = commandData[1];
                    if (!teamName.equals(teams.get(teamName).getName())) {
                        throw new IllegalArgumentException(String.format("Team %s does not exist.", teamName));
                    } else {
                        String playerName = commandData[2];
                        teams.get(teamName).removePlayer(playerName);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("Rating")) {
                try {
                    if (commandData.length > 1) {
                        String teamName = commandData[1];
                        if (teams.get(teamName) == null) {
                            throw new IllegalArgumentException(String.format("Team %s does not exist.", teamName));
                        } else {
                            System.out.println(String.format("%s - %d", teamName, Math.round(teams.get(teamName).calculateTeamRating())));
                        }
                    } else {
                        throw new IllegalArgumentException("A name should not be empty.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            currentLine = reader.readLine();
        }
    }
}
