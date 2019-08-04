package Algorithms.GraphsAndGraphAlgorithms.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DFSTopologicalSorting {
    public static void main(String[] args) throws IOException {
        Map<String, List<String>> graph = readGraph();
        Collection<String> result = topSort(graph);
        printResult(result);
    }

    public static Collection<String> topSort(Map<String, List<String>> graph) {
        Set<String> visited = new HashSet<>();
        Set<String> cycleNodes = new HashSet<>();
        LinkedList<String> sorted = new LinkedList<>();

        for (String node : graph.keySet()) {
            DFS(node, sorted, graph, visited, cycleNodes);
        }

        return sorted;
    }

    private static void DFS(String node, LinkedList<String> sorted, Map<String, List<String>> graph, Set<String> visited, Set<String> cycleNodes) {
        if (cycleNodes.contains(node)) {
            throw new IllegalArgumentException();
        }

        if (!visited.contains(node)) {
            visited.add(node);
            cycleNodes.add(node);

            for (String child: graph.get(node)) {
                DFS(child, sorted, graph, visited, cycleNodes);
            }

            cycleNodes.remove(node);
            sorted.addFirst(node);
        }
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
