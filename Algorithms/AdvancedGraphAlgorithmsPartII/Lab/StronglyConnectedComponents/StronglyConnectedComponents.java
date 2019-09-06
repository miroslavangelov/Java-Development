package Algorithms.AdvancedGraphAlgorithmsPartII.Lab.StronglyConnectedComponents;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class StronglyConnectedComponents {
    private static List<Integer>[] graph;
    private static List<Integer>[] reversedGraph;
    private static Deque<Integer> stack;
    private static boolean[] visited;
    private static List<List<Integer>> stronglyConnectedComponents;

    public static List<List<Integer>> findStronglyConnectedComponents(List<Integer>[] targetGraph) {
        stronglyConnectedComponents = new ArrayList<>();
        graph = targetGraph;
        visited = new boolean[graph.length];
        stack = new ArrayDeque<>();

        buildReverseGraph();
        for (int node = 0; node < graph.length; node++) {
            if (!visited[node]) {
                DFS(node);
            }
        }

        visited = new boolean[graph.length];
        while (!stack.isEmpty()) {
            int node = stack.pop();

            if (!visited[node]) {
                stronglyConnectedComponents.add(new ArrayList<>());
                reverseDFS(node);
            }
        }

        return stronglyConnectedComponents;
    }

    private static void buildReverseGraph() {
        reversedGraph = new ArrayList[graph.length];

        for (int i = 0; i < reversedGraph.length; i++) {
            reversedGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < reversedGraph.length; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                reversedGraph[graph[i].get(j)].add(i);
            }
        }
    }

    private static void DFS(int node) {
        if (!visited[node]) {
            visited[node] = true;

            for (int child: graph[node]) {
                DFS(child);
            }

            stack.push(node);
        }
    }

    private static void reverseDFS(int node) {
        if (!visited[node]) {
            visited[node] = true;
            stronglyConnectedComponents.get(stronglyConnectedComponents.size() - 1).add(node);

            for (int child: reversedGraph[node]) {
                reverseDFS(child);
            }
        }
    }
}
