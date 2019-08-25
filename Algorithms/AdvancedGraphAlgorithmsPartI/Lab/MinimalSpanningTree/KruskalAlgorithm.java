package Algorithms.AdvancedGraphAlgorithmsPartI.Lab.MinimalSpanningTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KruskalAlgorithm {

    public static List<Edge> kruskal(int numberOfVertices, List<Edge> edges) {
        List<Edge> spanningTree = new ArrayList<>();
        int[] parent = new int[numberOfVertices];

        for (int i = 0; i < numberOfVertices; i++) {
            parent[i] = i;
        }
        edges.sort(Comparator.naturalOrder());

        for (Edge edge: edges) {
            int stratNodeRoot = findRoot(edge.getStartNode(), parent);
            int endNodeRoot = findRoot(edge.getEndNode(), parent);

            if (stratNodeRoot != endNodeRoot) {
                spanningTree.add(edge);
                parent[stratNodeRoot] = endNodeRoot;
            }
        }

        return spanningTree;
    }

    public static int findRoot(int node, int[] parent) {
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
