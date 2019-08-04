package Algorithms.GraphsAndGraphAlgorithms.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TopologicalSorting {
    public static void main(String[] args) throws IOException {
        Map<String, List<String>> graph = readGraph();
        Collection<String> result = topSort(graph);
        printResult(result);
    }

    public static Collection<String> topSort(Map<String, List<String>> graph) {
        Map<String, Integer> predecessorCount = getPredecessorCount(graph);
        List<String> sorted = new ArrayList<>();

        while (true) {
            String nodeToRemove = predecessorCount.entrySet().stream()
                    .filter(element -> element.getValue() == 0)
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse(null);

            if (nodeToRemove == null) {
                break;
            }

            for (String child: graph.get(nodeToRemove)) {
                predecessorCount.put(child, predecessorCount.get(child) - 1);
            }

            graph.remove(nodeToRemove);
            predecessorCount.remove(nodeToRemove);
            sorted.add(nodeToRemove);
        }

        return sorted;
    }

    private static Map<String, Integer> getPredecessorCount(Map<String, List<String>> graph) {
        Map<String, Integer> predecessorCount = new LinkedHashMap<>();

        for (Map.Entry<String, List<String>> element: graph.entrySet()) {
            predecessorCount.putIfAbsent(element.getKey(), 0);

            for (String child: element.getValue()) {
                predecessorCount.putIfAbsent(child, 0);
                predecessorCount.put(child, predecessorCount.get(child) + 1);
            }
        }

        return predecessorCount;
    }

    private static Map<String, List<String>> readGraph() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, List<String>> graph = new LinkedHashMap<>();
        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            String[] graphData = reader.readLine().split(" -> | ->|, ");
            String vertex = graphData[0].replaceAll("\"", "");
            graph.putIfAbsent(vertex, new ArrayList<>());

            for (int j = 1; j < graphData.length; j++) {
                String currentChild = graphData[j].replaceAll("\"", "");
                graph.get(vertex).add(currentChild);
            }
        }

        return graph;
    }

    private static void printResult(Collection<String> result) {
        StringBuilder sb = new StringBuilder();
        sb.append("Topological sorting:").append(System.lineSeparator());

        for (String element: result) {
            sb.append(element).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        System.out.println(sb.toString());
    }
}
