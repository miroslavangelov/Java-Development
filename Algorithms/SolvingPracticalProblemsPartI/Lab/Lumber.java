package Algorithms.SolvingPracticalProblemsPartI.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Lumber {
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] data = reader.readLine().split(" ");
        int lumbersCount = Integer.parseInt(data[0]);
        int queriesCount = Integer.parseInt(data[1]);
        List<Log> logs = new ArrayList<>();
        List<Integer>[] graph = new ArrayList[lumbersCount + 1];

        for (int i = 1; i <= lumbersCount; i++) {
            String[] lumberData = reader.readLine().split(" ");
            int x1 = Integer.parseInt(lumberData[0]);
            int y1 = Integer.parseInt(lumberData[1]);
            int x2 = Integer.parseInt(lumberData[2]);
            int y2 = Integer.parseInt(lumberData[3]);
            Log log = new Log(i, x1, x2, y1, y2);
            graph[i] = new ArrayList<>();

            for (Log element: logs) {
                if (element.intersects(log)) {
                    graph[element.getId()].add(i);
                    graph[i].add(element.getId());
                }
            }

            logs.add(log);
        }

        boolean[] visited = new boolean[lumbersCount + 1];
        int[] id = new int[lumbersCount + 1];

        for (int node = 1; node <= lumbersCount; node++) {
            if (!visited[node]) {
                DFS(node, visited, graph, id);
                count++;
            }
        }

        for (int i = 0; i < queriesCount; i++) {
            String[] queryData = reader.readLine().split(" ");
            int from = Integer.parseInt(queryData[0]);
            int to = Integer.parseInt(queryData[1]);

            System.out.println(id[from] == id[to] ? "YES" : "NO");
        }
    }

    private static void DFS(int node, boolean[] visited, List<Integer>[] graph, int[] id) {
        visited[node] = true;
        id[node] = count;

        for (int child: graph[node]) {
            if (!visited[child]) {
                DFS(child, visited, graph, id);
            }
        }
    }

    private static class Log {
        private int id;
        private int x1;
        private int x2;
        private int y1;
        private int y2;

        Log(int id, int x1, int x2, int y1, int y2) {
            this.id = id;
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }

        public int getId() {
            return id;
        }

        public int getX1() {
            return x1;
        }

        public int getX2() {
            return x2;
        }

        public int getY1() {
            return y1;
        }

        public int getY2() {
            return y2;
        }

        public boolean intersects(Log other) {
            return this.getX1() <= other.getX2() && this.getX2() >= other.getX1() &&
                    this.getY1() >= other.getY2() && this.getY2() <= other.getY1();
        }
    }
}
