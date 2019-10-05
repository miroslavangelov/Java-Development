package Algorithms.ExamPreparations.ExamPreparationI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Robbery {
    private static long[][] graph;
    private static boolean[] isWatched;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] nodesData = reader.readLine().split(" ");
        graph = new long[nodesData.length][nodesData.length];
        isWatched = new boolean[nodesData.length];

        for (int i = 0; i < nodesData.length; i++) {
            String currentVertex = nodesData[i];
            if (currentVertex.charAt(currentVertex.length() - 1) == 'w') {
                isWatched[i] = true;
            }
        }

        int energy = Integer.parseInt(reader.readLine());
        int waitingEnergy = Integer.parseInt(reader.readLine());
        int startingNode = Integer.parseInt(reader.readLine());
        int endingNode = Integer.parseInt(reader.readLine());
        int edgesCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < edgesCount; i++) {
            String[] graphData = reader.readLine().split(" ");
            int from = Integer.parseInt(graphData[0]);
            int to = Integer.parseInt(graphData[1]);
            int cost = Integer.parseInt(graphData[2]);

            graph[from][to] = cost;
        }

        long requiredEnergy = dijkstra(startingNode, endingNode, waitingEnergy);

        if (energy >= requiredEnergy) {
            System.out.print(energy - requiredEnergy);
        } else {
            System.out.print(String.format("Busted - need %d more energy", requiredEnergy - energy));
        }
    }

    private static long dijkstra(int startingNode, int endingNode, int waitingEnergy) {
        long[] distance = new long[graph.length];
        boolean[] isOdd = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        distance[startingNode] = 0;
        isOdd[startingNode] = true;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(i -> distance[i]));
        queue.offer(startingNode);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            for (int i = 0; i < graph.length; i++) {
                long distanceToChild = graph[vertex][i];

                if (distanceToChild != 0) {
                    long totalDistance = distance[vertex] + distanceToChild;
                    boolean shouldWait = (isWatched[i] && !isOdd[vertex]) || (!isWatched[i] && isOdd[vertex]);

                    if (shouldWait) {
                        totalDistance += waitingEnergy;
                    }

                    if (distance[i] > totalDistance) {
                        queue.offer(i);
                        distance[i] = totalDistance;
                        isOdd[i] = shouldWait == isOdd[vertex];
                    }
                }
            }
        }

        return distance[endingNode];
    }
}
