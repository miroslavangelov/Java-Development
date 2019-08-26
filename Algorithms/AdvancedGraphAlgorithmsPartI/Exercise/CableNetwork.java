package Algorithms.AdvancedGraphAlgorithmsPartI.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CableNetwork {
    private static Map<Integer, List<Edge>> graph = new HashMap<>();
    private static Set<Integer> spanningTree = new HashSet<>();
    private static int totalBudget = 0;
    private static int usedBudget = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        totalBudget = Integer.parseInt(reader.readLine().split(": ")[1]);
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

            if (edgeParts.length > 3) {
                spanningTree.add(from);
                spanningTree.add(to);
            }
        }

        prim();
        System.out.println(String.format("Budget used: %d", usedBudget));
    }

    private static void prim() {
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparing(Edge::getCost));

        for (Integer vertex : spanningTree) {
            queue.addAll(graph.get(vertex));
        }

        while (queue.size() > 0) {
            Edge min = queue.poll();
            int nonTreeNode = -1;

            if (spanningTree.contains(min.getFirstVertex()) && !spanningTree.contains(min.getSecondVertex())) {
                nonTreeNode = min.getSecondVertex();
            }

            if (!spanningTree.contains(min.getFirstVertex()) && spanningTree.contains(min.getSecondVertex())) {
                nonTreeNode = min.getFirstVertex();
            }

            if (nonTreeNode == -1) {
                continue;
            }

            if (totalBudget >= min.cost) {
                totalBudget -= min.cost;
                usedBudget += min.cost;
            } else {
                break;
            }

            spanningTree.add(nonTreeNode);
            queue.addAll(graph.get(nonTreeNode));
        }
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
