package Algorithms.GraphsAndGraphAlgorithms.Lab;

import java.util.*;

public class ConnectedComponents {
    private static boolean[] visited;

    public static void main(String[] args) {
        List<List<Integer>> graph = readGraph();

        List<Deque<Integer>> connectedComponents = getConnectedComponents(graph);
        for (Deque<Integer> connectedComponent : connectedComponents) {
            System.out.println(connectedComponent);
        }
    }

    private static List<List<Integer>> readGraph() {
        Scanner in = new Scanner(System.in);

        List<List<Integer>> graph = new ArrayList<>();
        int n = Integer.parseInt(in.nextLine());
        for (int i = 0; i < n; i++) {
            List<Integer> connectedComponents = new ArrayList<>();

            String line = in.nextLine();
            if (line.equals("")) {
                graph.add(connectedComponents);
                continue;
            }
            String[] nodes = line.split(" ");

            for (String node : nodes) {
                connectedComponents.add(Integer.parseInt(node));
            }

            graph.add(connectedComponents);
        }
        return graph;
    }

    public static List<Deque<Integer>> getConnectedComponents(List<List<Integer>> graph) {
        List<Deque<Integer>> connectedComponents = new ArrayList<>();
        visited = new boolean[graph.size()];

        for (int startNode = 0; startNode < graph.size(); startNode++) {
            Deque<Integer> connectedComponent = new ArrayDeque<>();

            if (!visited[startNode]) {
                visited[startNode] = true;
                DFS(startNode, graph, connectedComponent);
                connectedComponent.offer(startNode);
            }

            if (!connectedComponent.isEmpty()) {
                connectedComponents.add(connectedComponent);
            }
        }

        return connectedComponents;
    }

    private static void DFS(int vertex, List<List<Integer>> graph, Deque<Integer> connectedComponent) {
        for (Integer child : graph.get(vertex)) {
            if (!visited[child]) {
                visited[child] = true;
                DFS(child, graph, connectedComponent);
                connectedComponent.offer(child);
            }
        }
    }
}
