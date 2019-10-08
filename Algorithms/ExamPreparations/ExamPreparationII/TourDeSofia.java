package Algorithms.ExamPreparations.ExamPreparationII;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class TourDeSofia {
    private static Set<Integer>[] graph;
    private static boolean[] visited;
    private static int[] distanceTo;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodesCount = Integer.parseInt(reader.readLine());
        int edgesCount = Integer.parseInt(reader.readLine());
        int startingNode = Integer.parseInt(reader.readLine());
        graph = new HashSet[nodesCount];
        for (int i = 0; i < nodesCount; i++) {
            graph[i] = new HashSet<>();
        }
        visited = new boolean[graph.length];
        distanceTo = new int[graph.length];

        for (int i = 0; i < edgesCount; i++) {
            String[] graphData = reader.readLine().split(" ");
            int from = Integer.parseInt(graphData[0]);
            int to = Integer.parseInt(graphData[1]);
            graph[from].add(to);
        }
        BFS(startingNode);

        boolean found = false;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < graph.length; i++) {
            if (visited[i] && graph[i].contains(startingNode) && distanceTo[i] + 1 < min) {
                found = true;
                min = distanceTo[i] + 1;
            }
        }

        if (found) {
            System.out.println(min);
        } else {
            int count = 0;
            for (int i = 0; i < visited.length; i++) {
               if (visited[i]) {
                   count++;
               }
            }
            System.out.println(count);
        }
    }

    private static void BFS(int start) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        distanceTo[start] = 0;
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int child: graph[node]) {
                if (!visited[child]) {
                    queue.offer(child);
                    visited[child] = true;
                    distanceTo[child] = distanceTo[node] + 1;
                }
            }
        }
    }
}
