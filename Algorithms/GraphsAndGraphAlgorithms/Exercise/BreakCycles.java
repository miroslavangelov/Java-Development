package Algorithms.GraphsAndGraphAlgorithms.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BreakCycles {
    private static Map<Character, List<Character>> graph = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();

        while (input != null && !input.equals("")) {
            String[] nodesData = input.split(" ");
            Character node = nodesData[0].charAt(0);
            List<Character> children = Arrays.stream(nodesData)
                    .skip(2)
                    .map(element -> element.charAt(0))
                    .sorted()
                    .collect(Collectors.toList());

            graph.putIfAbsent(node, new ArrayList<>());
            graph.put(node, children);
            input = reader.readLine();
        }

        List<String> result = new ArrayList<>();
        Object[] graphKeys = graph.keySet().toArray();

        for (Object node: graphKeys) {
            Object[] graphValues = graph.get(node).toArray();
            for (Object child: graphValues) {
                graph.get(node).remove(child);
                graph.get(child).remove(node);

                if (hasPath((Character) node, (Character) child)) {
                    result.add(String.format("%s - %s", node, child));
                } else {
                    graph.get(node).add((Character) child);
                    graph.get(child).add((Character) node);
                }
            }
        }

        System.out.println(String.format("Edges to remove: %d", result.size()));
        for (String removedEdge: result) {
            System.out.println(removedEdge);
        }
    }

    private static boolean hasPath(Character start, Character end) {
        Deque<Character> vertices = new ArrayDeque<>();
        Map<Character, Boolean> visited  = new HashMap<>();
        for (Character node : graph.keySet()) {
            visited.put(node, false);
        }

        vertices.offer(start);
        visited.put(start, true);

        while (vertices.size() > 0) {
            Character currentVertex = vertices.poll();

            for (Character child: graph.get(currentVertex)) {
                if (!visited.get(child)) {
                    visited.put(child, true);
                    vertices.offer(child);

                    if (child == end) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
