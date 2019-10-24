package Algorithms.Exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CheapTownTour {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int towns = Integer.parseInt(reader.readLine());
        int streets = Integer.parseInt(reader.readLine());
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < streets; i++) {
            String[] streetData = reader.readLine().split(" - ");
            int from = Integer.parseInt(streetData[0]);
            int to = Integer.parseInt(streetData[1]);
            int distance = Integer.parseInt(streetData[2]);
            Edge edge = new Edge(from, to, distance);

            edges.add(edge);
        }

        int minValue = kruskal(towns, edges);
        System.out.print(String.format("Total cost: %d", minValue));
    }

    private static int kruskal(int numberOfVertices, List<Edge> edges) {
        int[] parent = new int[numberOfVertices];
        int minimumSpanningTree = 0;

        for (int i = 0; i < numberOfVertices; i++) {
            parent[i] = i;
        }
        edges.sort(Comparator.naturalOrder());

        for (Edge edge: edges) {
            int startNodeRoot = findRoot(edge.getStartNode(), parent);
            int endNodeRoot = findRoot(edge.getEndNode(), parent);

            if (startNodeRoot != endNodeRoot) {
                minimumSpanningTree += edge.getDistance();
                parent[startNodeRoot] = endNodeRoot;
            }
        }

        return minimumSpanningTree;
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
    private int distance;

    Edge(int startNode, int endNode, int distance) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.distance = distance;
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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.distance, other.distance);
    }
}
