package Algorithms.GraphsAndGraphAlgorithms.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DistanceBetweenVertices {
    private static Map<Integer, Boolean> visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int p = Integer.parseInt(reader.readLine());
        Map<Integer, List<Integer>> graph = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] graphData = reader.readLine().split("[: ]");
            int vertex = Integer.parseInt(graphData[0]);

            graph.putIfAbsent(vertex, new ArrayList<>());
            for (int j = 1; j < graphData.length; j++) {
                int edge = Integer.parseInt(graphData[j]);
                graph.get(vertex).add(edge);
            }
        }

        for (int i = 0; i < p; i++) {
            String[] data = reader.readLine().split("-");
            int from = Integer.parseInt(data[0]);
            int to = Integer.parseInt(data[1]);
            visited = new HashMap<>();

            for (int vertex: graph.keySet()) {
                visited.put(vertex, false);
            }

            int distance = findDistance(graph, from, to);
            System.out.print(String.format("{%d, %d} -> %d%n", from, to, distance));
        }
    }

    private static int findDistance(Map<Integer, List<Integer>> graph, int from, int to) {
        Deque<Integer> vertices = new ArrayDeque<>();
        vertices.offer(from);
        int distance = 1;

        while (vertices.size() > 0) {
            List<Integer> children = new ArrayList<>();

            while (vertices.size() > 0) {
                int currentVertex = vertices.poll();

                for (int child: graph.get(currentVertex)) {
                    if (!visited.get(child)) {
                        if (child == to) {
                            return distance;
                        }
                        visited.put(child, true);
                        children.add(child);
                    }
                }
            }

            vertices.addAll(children);
            distance++;
        }

        return -1;
    }
}
