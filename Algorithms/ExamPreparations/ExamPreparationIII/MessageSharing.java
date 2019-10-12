package Algorithms.ExamPreparations.ExamPreparationIII;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class MessageSharing {
    private static Map<String, List<String>> graph = new HashMap<>();
    private static Map<String, Integer> peopleSteps = new HashMap<>();
    private static Deque<String> queue = new ArrayDeque<>();
    private static Set<String> unreachablePeople = new TreeSet<>();
    private static Set<String> lastPeople = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] peopleData = reader.readLine().split(": ")[1].split(", ");
        for (String person: peopleData) {
            graph.putIfAbsent(person, new ArrayList<>());
            peopleSteps.putIfAbsent(person, -1);
        }

        String[] connectionsData = reader.readLine().split(": ")[1].split(", ");
        for (String connection: connectionsData) {
            String[] currentConnection = connection.split(" - ");
            String firstPerson = currentConnection[0];
            String secondPerson = currentConnection[1];
            graph.get(firstPerson).add(secondPerson);
            graph.get(secondPerson).add(firstPerson);
        }

        String[] firstPeopleData = reader.readLine().split(": ")[1].split(", ");
        for (String person: firstPeopleData) {
            queue.offer(person);
            peopleSteps.put(person, 0);
        }

        int maxSteps = getMaxSteps();
        boolean isReceivedByAll = true;

        for (Map.Entry<String, Integer> pair: peopleSteps.entrySet()) {
            if (pair.getValue() == -1) {
                unreachablePeople.add(pair.getKey());
                isReceivedByAll = false;
            } else if (isReceivedByAll && pair.getValue() == maxSteps) {
                lastPeople.add(pair.getKey());
            }
        }

        StringBuilder sb = new StringBuilder();

        if (!isReceivedByAll) {
            sb.append(String.format("Cannot reach: %s", unreachablePeople.stream().map(Object::toString).collect(Collectors.joining(", "))));
        } else {
            sb.append(String.format("All people reached in %d steps%n", maxSteps))
                    .append(String.format("People at last step: %s", lastPeople.stream().map(Object::toString).collect(Collectors.joining(", "))));
        }
        System.out.print(sb);
    }

    private static int getMaxSteps() {
        int maxSteps = 0;

        while (!queue.isEmpty()) {
            String currentPerson = queue.poll();

            for (String child: graph.get(currentPerson)) {
                if (peopleSteps.get(child) == -1) {
                    queue.offer(child);
                    int step = peopleSteps.get(currentPerson) + 1;
                    if (step > maxSteps) {
                        maxSteps = step;
                    }
                    peopleSteps.put(child, step);
                }
            }
        }

        return maxSteps;
    }
}
