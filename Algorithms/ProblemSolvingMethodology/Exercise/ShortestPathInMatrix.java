package Algorithms.ProblemSolvingMethodology.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class ShortestPathInMatrix {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int rowsCount = Integer.parseInt(reader.readLine());
        int colsCount = Integer.parseInt(reader.readLine());
        int[][] matrix = new int[rowsCount][colsCount];
        Map<Integer, Integer> graphMatrix = new HashMap<>();

        for (int i = 0; i < rowsCount; i++) {
            int[] matrixData = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            matrix[i] = matrixData;
        }

        int graphLength = rowsCount * colsCount;
        int[][] graph = new int[graphLength][graphLength];
        int graphIndex = 0;

        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < colsCount; j++) {
                int currentMatrixNumber = matrix[i][j];

                if (j + 1 < colsCount) {
                    graph[graphIndex][graphIndex + 1] = currentMatrixNumber + matrix[i][j + 1];
                    graph[graphIndex + 1][graphIndex] = currentMatrixNumber + matrix[i][j + 1];
                }

                if (i + 1 < rowsCount) {
                    graph[graphIndex][graphIndex + colsCount] = currentMatrixNumber + matrix[i + 1][j];
                    graph[graphIndex + colsCount][graphIndex] = currentMatrixNumber + matrix[i + 1][j];
                }
                graphMatrix.put(graphIndex, currentMatrixNumber);
                graphIndex++;
            }
        }

        List<Integer> path = dijkstraAlgorithm(graph, 0, graphLength - 1);
        List<Integer> result = new ArrayList<>();
        for (int element: path) {
            result.add(graphMatrix.get(element));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Length: %d%n", result.stream().mapToInt(element -> element).sum()))
                .append(String.format("Path: %s", result.stream().map(Object::toString).collect(Collectors.joining(" "))));
        System.out.println(sb);
    }

    private static List<Integer> dijkstraAlgorithm(int[][] graph, int sourceNode, int destinationNode) {
        int[] distance = new int[graph.length];
        int[] prev = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            distance[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }
        distance[sourceNode] = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(i -> distance[i]));
        queue.offer(sourceNode);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            for (int i = 0; i < graph.length; i++) {
                int distanceToChild = graph[vertex][i];
                int totalDistance = distance[vertex] + distanceToChild;

                if (distanceToChild != 0 && distance[i] >= totalDistance) {
                    queue.offer(i);
                    prev[i] = vertex;
                    distance[i] = totalDistance;
                }
            }
        }

        if (prev[destinationNode] == -1) {
            return null;
        }

        LinkedList<Integer> path = new LinkedList<>();
        int current = destinationNode;
        path.addFirst(current);

        while (prev[current] != -1) {
            path.addFirst(prev[current]);
            current = prev[current];
        }

        return path;
    }
}
