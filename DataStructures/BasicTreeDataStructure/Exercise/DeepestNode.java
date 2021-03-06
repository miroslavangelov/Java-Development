package DataStructures.BasicTreeDataStructure.Exercise;

import DataStructures.BasicTreeDataStructure.Lab.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DeepestNode {
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

        Tree<Integer> root = new Tree<>(0);
        for (Tree<Integer> tree : treeMap.values()) {
            if (tree.getParent() == null) {
                root = tree;
                break;
            }
        }
        System.out.print(String.format("Deepest node: %d", findDeepestNode(root)));
    }

    private static int findDeepestNode(Tree<Integer> tree) {
        Map<Integer, Integer> nodeDepth = new LinkedHashMap<>();
        DFS(tree, nodeDepth, 0);

        return Collections.max(nodeDepth.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    private static void DFS(Tree<Integer> parentNode, Map<Integer, Integer> nodeDepth, int depth) {
        for (Tree<Integer> child : parentNode.getChildren()) {
            DFS(child, nodeDepth, depth + 1);
        }
        nodeDepth.putIfAbsent(parentNode.getValue(), depth);
    }
}
