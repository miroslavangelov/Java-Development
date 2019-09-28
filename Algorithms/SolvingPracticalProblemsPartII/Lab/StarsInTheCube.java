package Algorithms.SolvingPracticalProblemsPartII.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class StarsInTheCube {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int cubeSize = Integer.parseInt(reader.readLine());
        char[][][] cube = new char[cubeSize][cubeSize][cubeSize];

        for (int i = 0; i < cubeSize; i++) {
            String[] cubeData = reader.readLine().split(" \\| ");
            for (int j = 0; j < cubeSize; j++) {
                String[] cells = cubeData[j].split(" ");
                for (int k = 0; k < cubeSize; k++) {
                    cube[j][i][k] = cells[k].charAt(0);
                }
            }
        }

        Map<Character, Integer> starsPerChar = new TreeMap<>();
        for (int i = 1; i < cubeSize - 1; i++) {
            for (int j = 1; j < cubeSize - 1; j++) {
                for (int k = 1; k < cubeSize - 1; k++) {
                    char centerChar = cube[i][j][k];

                    if (cube[i + 1][j][k] == centerChar && cube[i - 1][j][k] == centerChar &&
                        cube[i][j + 1][k] == centerChar && cube[i][j - 1][k] == centerChar &&
                        cube[i][j][k + 1] == centerChar && cube[i][j][k - 1] == centerChar) {
                        starsPerChar.putIfAbsent(centerChar, 0);
                        starsPerChar.put(centerChar, starsPerChar.get(centerChar) + 1);
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append(starsPerChar.values().stream().mapToInt(element -> element).sum())
                .append(System.lineSeparator());
        starsPerChar.forEach((key, value) -> result.append(String.format("%s -> %d%n", key, value)));
        System.out.print(result);
    }
}
