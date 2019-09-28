package Algorithms.SolvingPracticalProblemsPartII.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ShortestPath {
    private static char[] path;
    private static char[] combinations;
    private static int combinationsCount = 0;
    private static StringBuilder result = new StringBuilder();
    private static List<Integer> indices = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        path = reader.readLine().toCharArray();
        for (int i = 0; i < path.length; i++) {
            if ('*' == path[i]) {
                indices.add(i);
            }
        }
        combinations = new char[indices.size()];
        solve(0);
        System.out.println(combinationsCount);
        System.out.print(result);
    }

    private static void solve(int index) {
        if (index == indices.size()) {
            combinationsCount++;
            for (int i = 0; i < indices.size(); i++) {
                int currentIndex = indices.get(i);
                path[currentIndex] = combinations[i];
            }
            result.append(path).append(System.lineSeparator());
            return;
        }

        combinations[index] = 'L';
        solve(index + 1);
        combinations[index] = 'R';
        solve(index + 1);
        combinations[index] = 'S';
        solve(index + 1);
    }
}
