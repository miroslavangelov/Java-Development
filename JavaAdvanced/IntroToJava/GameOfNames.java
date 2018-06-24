package JavaAdvanced.IntroToJava;

import java.util.Scanner;

public class GameOfNames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int playersCount = Integer.parseInt(scanner.nextLine());
        String winner = "";
        int maxScore = Integer.MIN_VALUE;
        for (int i = 0; i < playersCount; i++) {
            String player = scanner.nextLine();
            int score = 0;
            for (int j = 0; j < player.length(); j++) {
                char currentChar = player.charAt(j);
                int points = (int)currentChar;
                if (points %2 == 0) {
                    score += points;
                } else {
                    score -= points;
                }
            }
            int initialScore = Integer.parseInt(scanner.nextLine());
            score += initialScore;
            if (score > maxScore) {
                maxScore = score;
                winner = player;
            }
        }
        System.out.println(String.format("The winner is %s - %d points", winner, maxScore));
    }
}
