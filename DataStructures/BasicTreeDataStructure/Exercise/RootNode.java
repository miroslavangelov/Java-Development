package DataStructures.BasicTreeDataStructure.Exercise;

import DataStructures.BasicTreeDataStructure.Lab.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class RootNode {
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
        System.out.print(String.format("Root node: %d", root.getValue()));
    }
}
