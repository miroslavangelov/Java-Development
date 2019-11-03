package DataStructures.BasicTreeDataStructure.Exercise;

import DataStructures.BasicTreeDataStructure.Lab.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PathsWithGivenSum {
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

        System.out.println("Subtrees of sum " + sum + ": ");

        List<Integer> roots = new ArrayList<>();
        calculateSum(root, sum, roots);

        for (Integer node: roots) {
            StringBuilder output = new StringBuilder();
            System.out.println(print(treeMap.get(node), output));
        }
    }

    private static String print(Tree<Integer> parentNode, StringBuilder builder) {
        if (parentNode != null) {
            builder.append(parentNode.getValue()).append(" ");
            for (Tree<Integer> child : parentNode.getChildren()) {
                print(child, builder);
            }
        }
        return builder.toString();
    }

    private static int calculateSum(Tree<Integer> node, int sum, List<Integer> roots) {
        int currentSum = node.getValue();

        for (Tree<Integer> child : node.getChildren()) {
            currentSum += calculateSum(child, sum, roots);
        }

        if (currentSum == sum) {
            roots.add(node.getValue());
        }

        return currentSum;
    }
}
