package Algorithms.ExamPreparations.ExamPreparationI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Sticks {
    private static TreeMap<Integer, Set<Integer>> graph = new TreeMap<>();
    private static TreeMap<Integer, Set<Integer>> parents = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int verticesCount = Integer.parseInt(reader.readLine());
        int edgesCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < verticesCount; i++) {
            graph.putIfAbsent(i, new HashSet<>());
            parents.putIfAbsent(i, new HashSet<>());
        }

        for (int i = 0; i < edgesCount; i++) {
            String[] graphData = reader.readLine().split(" ");
            int from = Integer.parseInt(graphData[0]);
            int to = Integer.parseInt(graphData[1]);

            graph.get(from).add(to);
            parents.get(to).add(from);
        }

        List<Integer> result = new ArrayList<>();
        while (true) {
            Map.Entry<Integer, Set<Integer>> node = parents.entrySet().stream()
                    .filter(element -> element.getValue().isEmpty())
                    .min(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                    .orElse(null);

            if (node == null) {
                break;
            }

            int nodeKey = node.getKey();
            result.add(nodeKey);
            parents.remove(nodeKey);

            for (Integer child: graph.get(nodeKey)) {
                parents.get(child).remove(nodeKey);
            }
        }

        if (parents.size() > 0) {
            System.out.println("Cannot lift all sticks");
        }
        System.out.print(result.stream().map(Object::toString).collect(Collectors.joining(" ")));
    }
}
