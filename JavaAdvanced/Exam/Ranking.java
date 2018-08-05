package JavaAdvanced.Exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

public class Ranking {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        HashMap<String, String> contests = new HashMap<>();
        TreeMap<String, HashMap<String, Integer>> result = new TreeMap<>();

        while (!"end of contests".equals(currentLine)) {
            String[] contestData = currentLine.split(":");
            String contestName = contestData[0];
            String contestPassword = contestData[1];

            contests.putIfAbsent(contestName, contestPassword);
            currentLine = reader.readLine();
        }
        currentLine = reader.readLine();
        while (!"end of submissions".equals(currentLine)) {
            String[] data = currentLine.split("=>");
            String contestName = data[0];
            String contestPassword = data[1];
            String candidate = data[2];
            int points = Integer.parseInt(data[3]);

            if (contests.get(contestName) != null && contests.get(contestName).equals(contestPassword)) {
                result.putIfAbsent(candidate, new HashMap<>());
                result.get(candidate).putIfAbsent(contestName, 0);
                int currentContestPoints = result.get(candidate).get(contestName);
                if (points > currentContestPoints) {
                    result.get(candidate).put(contestName, points);
                }
            }
            currentLine = reader.readLine();
        }

        String bestCandidate = result.entrySet().stream()
                .sorted(Comparator
                        .comparing((HashMap.Entry<String, HashMap<String, Integer>> entry) ->
                                entry.getValue().values().stream().mapToInt(Integer::valueOf).sum(), Comparator.reverseOrder()))
                .findFirst().get().getKey();
        System.out.println(String.format("Best candidate is %s with total %d points.", bestCandidate, result.get(bestCandidate).values().stream().mapToInt(Integer::valueOf).sum()));
        System.out.println("Ranking: ");
        result.entrySet().stream()
                .forEach(candidate -> {
                    System.out.println(candidate.getKey());
                    candidate.getValue().entrySet().stream()
                        .sorted((firstCandidate, secondCandidate) -> secondCandidate.getValue().compareTo(firstCandidate.getValue()))
                        .forEach(contest -> System.out.println(String.format("#  %s -> %d", contest.getKey(), contest.getValue())));
                });
    }
}
