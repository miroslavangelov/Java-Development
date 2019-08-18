package Algorithms.GraphsAndGraphAlgorithms.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CyclesInAGraph {
    private static Map<String, List<String>> graph = new HashMap<>();
    private static Set<String> visited = new HashSet<>();
    private static Set<String> currentVisited = new HashSet<>();
    private static boolean isAcyclic;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();

        while (input != null && !input.equals("")) {
            String[] graphData = input.split("[-–]");
            graph.putIfAbsent(graphData[0], new ArrayList<>());
            graph.putIfAbsent(graphData[1], new ArrayList<>());
            graph.get(graphData[0]).add(graphData[1]);
            graph.get(graphData[1]).add(graphData[0]);

            input = reader.readLine();
        }

        for (String vertex: graph.keySet()) {
            checkCycle(vertex, null);
        }

        System.out.println(String.format("Acyclic: %s", isAcyclic ? "No" : "Yes"));
    }

    private static void checkCycle(String vertex, String parent) {
        if (currentVisited.contains(vertex)) {
            isAcyclic = true;
            return;
        }

        if (visited.contains(vertex) || isAcyclic) {
            return;
        }

        visited.add(vertex);
        currentVisited.add(vertex);

        for (String child: graph.get(vertex)) {
            if (!child.equals(parent)) {
                checkCycle(child, vertex);
            }
        }

        currentVisited.remove(vertex);
    }
}
