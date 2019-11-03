package DataStructures.BasicTreeDataStructure.Exercise;

import DataStructures.BasicTreeDataStructure.Lab.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class SubtreesWithGivenSum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(reader.readLine());
        Map<Integer, Tree<Integer>> treeMap = new HashMap<>();

        for (int i = 0; i < nodes - 1; i++) {
            String[] edgeData = reader.readLine().split(" ");
            int from = Integer.parseInt(edgeData[0]);
            int to = Integer.parseInt(edgeData[1]);

            treeMap.putIfAbsent(from, new Tree<>(from));
            treeMap.putIfAbsent(to, new Tree<>(to));
            treeMap.get(from).addChild(treeMap.get(to));
        }

        int sum = Integer.parseInt(reader.readLine());
        Tree<Integer> root = new Tree<>(0);

        for (Tree<Integer> tree : treeMap.values()) {
            if (tree.getParent() == null) {
                root = tree;
                break;
            }
        }

        List<Integer> paths = new ArrayList<>();
        calculateSum(root, sum, 0, paths);
        StringBuilder output = new StringBuilder();

        output.append(String.format("Paths of sum %d:", sum))
                .append(System.lineSeparator());
        for (Integer value : paths) {
            Tree<Integer> currentNode = treeMap.get(value);
            List<Integer> path = new ArrayList<>();

            while (currentNode != null) {
                path.add(currentNode.getValue());
                currentNode = currentNode.getParent();
            }
            Collections.reverse(path);
            output.append(path.stream().map(Object::toString).collect(Collectors.joining(" ")))
                    .append(System.lineSeparator());
        }
        System.out.print(output);
    }

    private static void calculateSum(Tree<Integer> node, int sum, int currentSum, List<Integer> path) {
        currentSum += node.getValue();

        if (currentSum == sum && node.getChildren().isEmpty()) {
            path.add(node.getValue());
        }

        for (Tree<Integer> child : node.getChildren()) {
            calculateSum(child, sum, currentSum, path);
        }
    }
}
