package JavaAdvanced.Abstraction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlusRemove {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<char[]> originalMatrix = new ArrayList<>();
        List<char[]> copyMatrix = new ArrayList<>();
        String inputCommand = reader.readLine();
        while (!inputCommand.equals("END")) {
            originalMatrix.add(inputCommand.toCharArray());
            copyMatrix.add(inputCommand.toUpperCase().toCharArray());
            inputCommand = reader.readLine();
        }
        for (int i = 0; i < originalMatrix.size() - 2; i++) {
            for (int j = 0; j < originalMatrix.get(i).length; j++) {
                try {
                    char a = copyMatrix.get(i)[j];
                    char b = copyMatrix.get(i + 1)[j - 1];
                    char c = copyMatrix.get(i + 1)[j];
                    char d = copyMatrix.get(i + 1)[j + 1];
                    char e = copyMatrix.get(i + 2)[j];
                    if (a == b && b == c && c == d && d == e) {
                        originalMatrix.get(i)[j] = '\0';
                        originalMatrix.get(i + 1)[j - 1] = '\0';
                        originalMatrix.get(i + 1)[j] = '\0';
                        originalMatrix.get(i + 1)[j + 1] = '\0';
                        originalMatrix.get(i + 2)[j] = '\0';
                    }
                } catch (IndexOutOfBoundsException error) {

                }
            }
        }
        for (char[] currentLine: originalMatrix) {
            System.out.println(Arrays.toString(currentLine).replaceAll("[\\[\\], \0]", ""));
        }
    }
}
