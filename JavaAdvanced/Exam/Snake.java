package JavaAdvanced.Exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Snake {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int screenSize = Integer.parseInt(reader.readLine());
        String[][] screen = new String[screenSize][screenSize];
        String[] commands = reader.readLine().split(", ");
        int currentPositionRow = 0;
        int currentPositionCol = 0;
        int snakeSize = 1;
        int foodCount = 0;

        for (int i = 0; i < screenSize; i++) {
            String[] currentLine = reader.readLine().split(" ");
            for (int j = 0; j < currentLine.length; j++) {
                screen[i][j] = currentLine[j];
                if ("s".equals(currentLine[j])) {
                    currentPositionRow = i;
                    currentPositionCol = j;
                } else if ("f".equals(currentLine[j])) {
                    foodCount += 1;
                }
            }
        }
        int foodLeft = foodCount;

        for (int i = 0; i < commands.length; i++) {
            String currentCommand = commands[i];
            switch (currentCommand) {
                case "up":
                    currentPositionRow -= 1;
                    if (currentPositionRow < 0) {
                        currentPositionRow = screenSize - 1;
                    }
                    break;
                case "down":
                    currentPositionRow += 1;
                    if (currentPositionRow > screenSize - 1) {
                        currentPositionRow = 0;
                    }
                    break;
                case "left":
                    currentPositionCol -= 1;
                    if (currentPositionCol < 0) {
                        currentPositionCol = screenSize - 1;
                    }
                    break;
                case "right":
                    currentPositionCol += 1;
                    if (currentPositionCol > screenSize - 1) {
                        currentPositionCol = 0;
                    }
                    break;
            }
            switch (screen[currentPositionRow][currentPositionCol]) {
                case "f":
                    snakeSize += 1;
                    screen[currentPositionRow][currentPositionCol] = "*";
                    foodLeft -= 1;
                    break;
                case "e": System.out.println("You lose! Killed by an enemy!");return;
            }

            if (foodLeft == 0) {
                System.out.println(String.format("You win! Final snake length is %d", snakeSize));
                return;
            }
        }
        System.out.println(String.format("You lose! There is still %d food to be eaten.", (foodLeft)));
    }
}
