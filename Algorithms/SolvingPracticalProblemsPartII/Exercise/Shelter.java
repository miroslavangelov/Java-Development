package Algorithms.SolvingPracticalProblemsPartII.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Shelter {
    private static double[] times;
    private static double[][] soldiersTimes;
    private static Point[] soldiers;
    private static Point[] shelters;
    private static double timeLimit;
    private static int soldiersCount;
    private static int sheltersCount;
    private static int shelterCapacity;
    private static List<Integer>[] edges;
    private static int[][] graph;
    private static int[] childCounter;
    private static int[] levels;
    private static int sink;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] warzoneData = reader.readLine().split(" ");
        soldiersCount = Integer.parseInt(warzoneData[0]);
        sheltersCount = Integer.parseInt(warzoneData[1]);
        shelterCapacity = Integer.parseInt(warzoneData[2]);

        soldiers = new Point[soldiersCount];
        for (int i = 0; i < soldiersCount; i++) {
            String[] soldierData = reader.readLine().split(" ");
            int x = Integer.parseInt(soldierData[0]);
            int y = Integer.parseInt(soldierData[1]);
            soldiers[i] = new Point(x, y);
        }

        shelters = new Point[sheltersCount];
        for (int i = 0; i < sheltersCount; i++) {
            String[] shelterData = reader.readLine().split(" ");
            int x = Integer.parseInt(shelterData[0]);
            int y = Integer.parseInt(shelterData[1]);
            shelters[i] = new Point(x, y);
        }

        sink = soldiersCount + sheltersCount + 1;
        childCounter = new int[sink + 1];
        levels = new int[sink + 1];
        getTimes(soldiersCount, sheltersCount);
        double bestTime = getBestTime();
        System.out.printf("%.6f%n", bestTime);
    }

    private static double getBestTime() {
        double bestTime = times[times.length - 1];
        int low = 0;
        int high = times.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            timeLimit = times[mid];
            int maxFlow = dinicMaxFlow(0, sink - 1);
            if (maxFlow < soldiersCount) {
                low = mid + 1;
            } else {
                high = mid - 1;
                bestTime = Math.min(timeLimit, bestTime);
            }
        }
        return bestTime;
    }

    private static int dinicMaxFlow(int source, int destination) {
        buildGraph();
        int result = 0;
        while (BFS(source, destination)) {
            for (int i = 0; i < childCounter.length; i++) {
                childCounter[i] = 0;
            }
            int delta;
            do {
                delta = DFS(source, Integer.MAX_VALUE);
                result += delta;
            } while (delta != 0);
        }
        return result;
    }

    private static boolean BFS(int source, int destination) {
        for (int i = 0; i < levels.length; i++) {
            levels[i] = -1;
        }
        levels[source] = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(source);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();

            for (int i = 0; i < edges[currentNode].size(); i++) {
                int child = edges[currentNode].get(i);
                if (levels[child] < 0 && graph[currentNode][child] > 0) {
                    levels[child] = levels[currentNode] + 1;
                    queue.offer(child);
                }
            }
        }
        return levels[destination] >= 0;
    }

    private static int DFS(int source, int flow) {
        if (source == sink) {
            return flow;
        }
        for (int i = childCounter[source]; i < edges[source].size(); i++, childCounter[source]++) {
            int child = edges[source].get(i);

            if (graph[source][child] <= 0) {
                continue;
            }

            if (levels[child] == levels[source] + 1) {
                int augmentationPathFlow = DFS(child, Math.min(flow, graph[source][child]));
                if (augmentationPathFlow > 0) {
                    graph[source][child] -= augmentationPathFlow;
                    graph[child][source] += augmentationPathFlow;

                    return augmentationPathFlow;
                }
            }
        }

        return 0;
    }

    private static void getTimes(int soldiersCount, int sheltersCount) {
        soldiersTimes = new double[soldiersCount][sheltersCount];
        times = new double[soldiersCount * sheltersCount];
        int timeIndex = 0;

        for (int i = 0; i < soldiersCount; i++) {
            for (int j = 0; j < sheltersCount; j++) {
                int soldierX = soldiers[i].getX();
                int soldierY = soldiers[i].getY();
                int shelterX = shelters[j].getX();
                int shelterY = shelters[j].getY();
                double time = Math.sqrt(Math.pow(Math.abs(soldierX - shelterX), 2) + Math.pow(Math.abs(soldierY - shelterY), 2));

                times[timeIndex++] = time;
                soldiersTimes[i][j] = time;
            }
        }
        Arrays.sort(times);
    }

    private static void buildGraph() {
        graph = new int[sink + 1][sink + 1];
        edges = new ArrayList[sink + 1];
        edges[0] = new ArrayList<>();

        for (int i = 1; i <= soldiersCount; i++) {
            edges[i] = new ArrayList<>();
            edges[0].add(i);
            edges[i].add(0);
            graph[0][i] = 1;
        }

        edges[sink] = new ArrayList<>();
        for (int i = 1; i <= sheltersCount; i++) {
            edges[soldiersCount + i] = new ArrayList<>();
            edges[soldiersCount + i].add(sink);
            edges[sink].add(soldiersCount + i);
            graph[i + soldiersCount][graph.length - 1] = shelterCapacity;
        }

        for (int i = 1; i <= soldiersCount; i++) {
            for (int j = 1; j <= sheltersCount; j++) {
                if (soldiersTimes[i - 1][j - 1] <= timeLimit) {
                    edges[i].add(soldiersCount + j);
                    edges[soldiersCount + j].add(i);
                    graph[i][j + soldiersCount] = 1;
                }
            }
        }
    }
}

class Point {
    private int x;
    private int y;

    Point (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
