package Algorithms.ProblemSolvingMethodology.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Blocks {
    private static final int lettersToChoose = 4;
    private static char[] letters;
    private static int lettersCount;
    private static char[] result;
    private static boolean[] usedElements;
    private static Set<String> variations = new TreeSet<>();
    private static Set<String> rotations = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        lettersCount = Integer.parseInt(reader.readLine());
        letters = new char[lettersCount];
        result = new char[lettersToChoose];
        usedElements = new boolean[letters.length];
        for (int i = 0; i < lettersCount; i++) {
            letters[i] = (char)('A' + i);
        }

        generateVariations(0);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Number of blocks: %d%n", variations.size()));
        for (String variation : variations) {
            sb.append(variation).append(System.lineSeparator());
        }
        System.out.print(sb);
    }

    private static void generateVariations(int index) {
        if (index >= lettersToChoose) {
            if (!rotations.contains(String.valueOf(result))) {
                variations.add(String.valueOf(result));
                addAllRotations();
            }
        } else {
            for (int i = 0; i < letters.length; i++) {
                if (!usedElements[i]) {
                    usedElements[i] = true;
                    result[index] = letters[i];
                    generateVariations(index + 1);
                    usedElements[i] = false;
                }
            }
        }
    }

    private static void addAllRotations() {
        for (int i = 0; i < lettersToChoose; i++) {
            char temp = result[0];
            result[0] = result[1];
            result[1] = result[2];
            result[2] = result[3];
            result[3] = temp;
            rotations.add(String.valueOf(result));
        }
    }
}
