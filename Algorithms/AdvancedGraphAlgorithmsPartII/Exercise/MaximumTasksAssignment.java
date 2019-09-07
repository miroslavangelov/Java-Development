package Algorithms.AdvancedGraphAlgorithmsPartII.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MaximumTasksAssignment {
    private static int[][] graph;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int personsCount = Integer.parseInt(reader.readLine().split(" ")[1]);
        int tasksCount = Integer.parseInt(reader.readLine().split(" ")[1]);
        int nodes = personsCount + tasksCount + 2;
        graph = new int[nodes][nodes];

        for (int i = 0; i < personsCount; i++) {
            graph[0][i + 1] = 1;
        }
        for (int i = 0; i < tasksCount; i++) {
            graph[i + personsCount + 1][graph.length - 1] = 1;
        }

        for (int person = 0; person < personsCount; person++) {
            char[] currentLine = reader.readLine().toCharArray();

            for (int task = 0; task < tasksCount; task++) {
                if (currentLine[task] == 'Y') {
                    graph[person + 1][task + personsCount + 1] = 1;
                }
            }
        }

        parent = new int[graph.length];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        int start = 0;
        int end = graph.length - 1;

        while (BFS(start, end)) {
            int currentNode = end;

            while (currentNode != start) {
                int prevNode = parent[currentNode];

                graph[prevNode][currentNode] = 0;
                graph[currentNode][prevNode] = 1;
                currentNode = prevNode;
            }
        }

        Deque<Integer> queue = new ArrayDeque<>();
        List<String> result = new ArrayList<>();
        boolean[] visited = new boolean[graph.length];
        queue.offer(end);
        visited[end] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int child = 0; child < graph.length; child++) {
                if (graph[node][child] > 0 && !visited[child]) {
                    queue.offer(child);
                    visited[child] = true;

                    if (node != end && node != start && child != start && child != end) {
                        result.add(String.format("%s-%s", (char) (child - 1 + 'A'), node - personsCount));
                    }
                }
            }
        }

        result.sort(Comparator.naturalOrder());
        for (String str : result) {
            System.out.println(str);
        }
    }

    private static boolean BFS(int start, int end) {
        boolean[] visited = new boolean[graph.length];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int child = 0; child < graph[node].length; child++) {
                if (graph[node][child] > 0 && !visited[child]) {
                    queue.offer(child);
                    parent[child] = node;
                    visited[child] = true;
                }
            }
        }

        return visited[end];
    }
}
