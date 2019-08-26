package Algorithms.AdvancedGraphAlgorithmsPartI.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ModifiedKruskalAlgorithm {
    private static Map<Integer, List<Edge>> graph = new HashMap<>();
    private static List<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodesCount = Integer.parseInt(reader.readLine().split(": ")[1]);
        int edgesCount = Integer.parseInt(reader.readLine().split(": ")[1]);

        for (int i = 0; i < edgesCount; i++) {
            String[] edgeParts = reader.readLine().split(" ");
            int from = Integer.parseInt(edgeParts[0]);
            int to = Integer.parseInt(edgeParts[1]);
            int cost = Integer.parseInt(edgeParts[2]);
            Edge edge = new Edge(from, to, cost);

            graph.putIfAbsent(from, new ArrayList<>());
            graph.putIfAbsent(to, new ArrayList<>());
            graph.get(from).add(edge);
            graph.get(to).add(edge);
            edges.add(edge);
        }

        kruskal();
    }

    private static void kruskal() {
        int[] parent = new int[graph.size()];
        int totalWeight = 0;

        for (int i = 0; i < graph.size(); i++) {
            parent[i] = i;
        }
        edges.sort(Comparator.comparing(Edge::getCost));

        for (Edge edge: edges) {
            int startNodeRoot = findRoot(edge.getFirstVertex(), parent);
            int endNodeRoot = findRoot(edge.getSecondVertex(), parent);

            if (startNodeRoot != endNodeRoot) {
                totalWeight += edge.getCost();
                parent[startNodeRoot] = endNodeRoot;
            }
        }

        System.out.println(String.format("Minimum spanning forest weight: %d", totalWeight));
    }

    private static int findRoot(int node, int[] parent) {
        int root = node;

        while (parent[root] != root) {
            root = parent[root];
        }

        while (node != root) {
            int previousParent = parent[node];
            parent[node] = root;
            node = previousParent;
        }

        return root;
    }

    private static class Edge {
        private int firstVertex;
        private int secondVertex;
        private int cost;

        Edge(int from, int to, int cost) {
            this.firstVertex = from;
            this.secondVertex = to;
            this.cost = cost;
        }

        int getFirstVertex() {
            return firstVertex;
        }

        int getSecondVertex() {
            return secondVertex;
        }

        int getCost() {
            return cost;
        }
    }
}
