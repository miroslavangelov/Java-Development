package Algorithms.SolvingPracticalProblemsPartII.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Renewal {
    private static int[][] graph;
    private static int[][] buildingCosts;
    private static int[][] destroyingCosts;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int citiesCount = Integer.parseInt(reader.readLine());
        graph = new int[citiesCount][citiesCount];
        buildingCosts = new int[citiesCount][citiesCount];
        destroyingCosts = new int[citiesCount][citiesCount];

        for (int i = 0; i < citiesCount; i++) {
            int[] graphData = Arrays.stream(reader.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            graph[i] = graphData;
        }

        for (int i = 0; i < citiesCount; i++) {
            String[] buildingCostsData = reader.readLine().split("");

            for (int j = 0; j < citiesCount; j++) {
                buildingCosts[i][j] = convertChar(buildingCostsData[j]);
            }
        }

        for (int i = 0; i < citiesCount; i++) {
            String[] destroyingCostsData = reader.readLine().split("");

            for (int j = 0; j < citiesCount; j++) {
                destroyingCosts[i][j] = convertChar(destroyingCostsData[j]);
            }
        }
        Set<Edge> edges = getEdges();
        int totalCost = kruskal(edges);
        System.out.println(totalCost);
    }

    private static Set<Edge> getEdges() {
        Set<Edge>edges = new TreeSet<>();

        for (int row = 0; row < graph.length - 1; row++) {
            for (int col = row + 1; col < graph.length; col++) {
                int cost;

                if (graph[row][col] == 0) {
                    cost = buildingCosts[row][col];
                } else {
                    cost = -destroyingCosts[row][col];
                }
                Edge edge = new Edge(row, col, cost);
                edges.add(edge);
            }
        }

        return edges;
    }

    private static int convertChar(String element) {
        char ch = element.charAt(0);
        int cost;

        if (Character.isUpperCase(ch)) {
            cost = ch - 'A';
        } else {
            cost = ch - 'a' + 26;
        }

        return cost;
    }

    private static int kruskal(Set<Edge> edges) {
        int totalCost = 0;
        int[] parent = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            parent[i] = i;
        }

        for (Edge edge: edges) {
            int startNodeRoot = findRoot(edge.getStartNode(), parent);
            int endNodeRoot = findRoot(edge.getEndNode(), parent);
            int cost = edge.getWeight();

            if (startNodeRoot != endNodeRoot) {
                parent[startNodeRoot] = endNodeRoot;

                if (cost > 0) {
                    totalCost += cost;
                }
            } else if (cost < 0) {
                totalCost -= cost;
            }
        }

        return totalCost;
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
}

class Edge implements Comparable<Edge> {
    private int startNode;
    private int endNode;
    private int weight;

    Edge(int startNode, int endNode, int weight) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.weight = weight;
    }

    public int getStartNode() {
        return startNode;
    }

    public void setStartNode(int startNode) {
        this.startNode = startNode;
    }

    public int getEndNode() {
        return endNode;
    }

    public void setEndNode(int endNode) {
        this.endNode = endNode;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        int result = Integer.compare(this.weight, other.weight);

        if (result == 0) {
            result = Integer.compare(this.startNode, other.startNode);

            if (result == 0) {
                result = Integer.compare(this.endNode, other.endNode);
            }
        }

        return result;
    }
}
