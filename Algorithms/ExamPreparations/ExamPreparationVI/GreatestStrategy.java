package Algorithms.ExamPreparations.ExamPreparationVI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GreatestStrategy {
    private static Map<Integer, Set<Integer>> graph = new HashMap<>();
    private static Map<Integer, Set<Integer>> disconnected = new HashMap<>();

    public static void main(String []args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] graphData = reader.readLine().split(" ");
        int numberOfAreas = Integer.parseInt(graphData[0]);
        int numberOfConnections = Integer.parseInt(graphData[1]);
        int startingArea = Integer.parseInt(graphData[2]);

        for (int i = 1; i <= numberOfAreas; i++) {
            graph.putIfAbsent(i, new HashSet<>());
            disconnected.putIfAbsent(i, new HashSet<>());
        }

        for (int i = 0; i < numberOfConnections; i++) {
            String[] connectionData = reader.readLine().split(" ");
            int from = Integer.parseInt(connectionData[0]);
            int to = Integer.parseInt(connectionData[1]);

            graph.get(from).add(to);
            graph.get(to).add(from);
            disconnected.get(from).add(to);
            disconnected.get(to).add(from);
        }

        DFS(startingArea, startingArea, new HashSet<>());

        int max = 0;
        Set<Integer> visited = new HashSet<>();

        for (Integer node: disconnected.keySet()) {
            if (!visited.contains(node)) {
                int value = getValue(node, visited);

                if (value > max) {
                    max = value;
                }
            }
        }
        System.out.print(max);
    }

    private static int getValue(int node, Set<Integer> visited) {
        int value = node;
        visited.add(node);

        for (Integer child: disconnected.get(node)) {
            if (!visited.contains(child)) {
                value += getValue(child, visited);
            }
        }

        return value;
    }

    private static int DFS(int node, int parent, Set<Integer> visited) {
        int totalNodes = 1;
        visited.add(node);

        for (Integer child: graph.get(node)) {
            if (!visited.contains(child) && child != parent) {
                int subTreeNodes = DFS(child, node, visited);

                if (subTreeNodes % 2 == 0) {
                    disconnected.get(node).remove(child);
                    disconnected.get(child).remove(node);
                }

                totalNodes += subTreeNodes;
            }
        }

        return totalNodes;
    }
}