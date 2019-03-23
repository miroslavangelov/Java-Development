package JavaAdvanced.Abstraction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TerroristsWin {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder text = new StringBuilder(reader.readLine());

        while (true) {
            int firstPipeIndex = text.indexOf("|");

            if (firstPipeIndex != -1) {
                int lastPipeIndex = text.indexOf("|", firstPipeIndex + 1);
                char[] bomb = text.substring(firstPipeIndex + 1, lastPipeIndex).toCharArray();
                int bombSum = 0;

                for (int i = 0; i < bomb.length; i++) {
                    int currentCharValue = (int) bomb[i];
                    bombSum += currentCharValue;
                }
                int bombDamage = bombSum % 10;
                int startIndex = Math.max(0, firstPipeIndex - bombDamage);
                int endIndex = Math.min(text.length() - 1, lastPipeIndex + bombDamage);

                for (int j = startIndex; j <= endIndex; j++) {
                    text.setCharAt(j, '.');
                }
            } else {
                break;
            }
        }
        System.out.println(text);
    }
}
