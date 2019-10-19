package Algorithms.ExamPreparations.ExamPreparationV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Sowing {
    private static int seedsCount;
    private static char[] field;
    private static List<Integer> goodSoilIndices = new ArrayList<>();
    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        seedsCount = Integer.parseInt(reader.readLine().trim());
        field = reader.readLine().replace(" ", "").toCharArray();
        for (int i = 0; i < field.length; i++) {
            if (field[i] == '1') {
                goodSoilIndices.add(i);
            }
        }
        generate(0, 0);
        System.out.print(result);
    }

    private static void generate(int index, int sownSeeds) {
        if (sownSeeds == seedsCount) {
            result.append(field);
            result.append(System.lineSeparator());
            return;
        }

        if (index == goodSoilIndices.size()) {
            return;
        }

        int goodSoilIndex = goodSoilIndices.get(index);
        boolean isPrevSeed = goodSoilIndex > 0 && field[goodSoilIndex - 1] == '.';

        if (!isPrevSeed) {
            field[goodSoilIndex] = '.';
            generate(index + 1, sownSeeds + 1);
            field[goodSoilIndex] = '1';
        }

        generate(index + 1, sownSeeds);
    }
}
