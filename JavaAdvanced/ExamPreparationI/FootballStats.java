package JavaAdvanced.ExamPreparationI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class FootballStats {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        HashMap<String, ArrayList<String>> stats = new HashMap<>();

        while(!"Season End".equals(currentLine)) {
            String[] matchData = currentLine.split(" result ");
            String[] teamsData = matchData[0].split(" - ");
            String firstTeam = teamsData[0];
            String secondTeam = teamsData[1];
            String[] resultData = matchData[1].split(":");
            int firstTeamGoals = Integer.parseInt(resultData[0]);
            int secondTeamGoals = Integer.parseInt(resultData[1]);
            stats.putIfAbsent(firstTeam, new ArrayList<>());
            stats.get(firstTeam).add(secondTeam + " -> " + firstTeamGoals + ":" + secondTeamGoals);

            stats.putIfAbsent(secondTeam, new ArrayList<>());
            stats.get(secondTeam).add(firstTeam + " -> " + secondTeamGoals + ":" + firstTeamGoals);

            currentLine = reader.readLine();
        }
        String[] teamsToPrint = reader.readLine().split(", ");

        for (int i = 0; i < teamsToPrint.length; i++) {
            String currentTeam = teamsToPrint[i];
            stats.get(currentTeam).stream()
                    .sorted((firstTeam, secondTeam) -> String.valueOf(firstTeam.charAt(0)).compareTo(String.valueOf(secondTeam.charAt(0))))
                    .forEach(opponent -> System.out.println(String.format("%s - %s", currentTeam, opponent)));
        }
    }
}
