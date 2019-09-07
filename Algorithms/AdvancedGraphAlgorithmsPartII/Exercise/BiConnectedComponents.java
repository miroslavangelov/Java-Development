package Algorithms.AdvancedGraphAlgorithmsPartII.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BiConnectedComponents {
    private static List<Integer>[] graph;
    private static boolean[] visited;
    private static int[] depths;
    private static int[] lowpoint;
    private static int[] parent;
    private static List<List<Integer>> biConnectedComponents = new ArrayList<>();
    private static List<AbstractMap.SimpleEntry<Integer, Integer>> component = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodesCount = Integer.parseInt(reader.readLine().split(" ")[1]);
        int edgesCount = Integer.parseInt(reader.readLine().split(" ")[1]);
        graph = new ArrayList[nodesCount];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        visited = new boolean[graph.length];
        depths = new int[graph.length];
        lowpoint = new int[graph.length];
        parent = new int[graph.length];

        for (int i = 0; i < edgesCount; i++) {
            String[] nodesData = reader.readLine().split(" ");
            int parent = Integer.parseInt(nodesData[0]);
            int child = Integer.parseInt(nodesData[1]);
            graph[parent].add(child);
            graph[child].add(parent);
        }

        DFS(0, 0);
        System.out.println(String.format("Number of bi-connected components: %d", biConnectedComponents.size()));
    }

    private static void DFS(int node, int depth) {
        visited[node] = true;
        depths[node] = depth;
        lowpoint[node] = depth;

        for (int child: graph[node]) {
            if (!visited[child]) {
                parent[child] = node;
                DFS(child, depth + 1);
                component.add(new AbstractMap.SimpleEntry<>(node, child));

                if (lowpoint[child] >= depths[node]) {
                    if (!component.isEmpty()) {
                        List<Integer> newComponent = new ArrayList<>();
                        AbstractMap.SimpleEntry<Integer, Integer> edge = component.get(0);
                        newComponent.add(edge.getKey());

                        do {
                            edge = component.remove(0);
                            newComponent.add(edge.getValue());
                        } while (!component.isEmpty() && (edge.getKey() != node || Objects.equals(component.get(0).getKey(), edge.getValue())));

                        biConnectedComponents.add(newComponent);
                    }
                }
                lowpoint[node] = Math.min(lowpoint[node], lowpoint[child]);
            } else if (child != (parent[node])) {
                lowpoint[node] = Math.min(lowpoint[node], depths[child]);
            }
        }
    }
}
