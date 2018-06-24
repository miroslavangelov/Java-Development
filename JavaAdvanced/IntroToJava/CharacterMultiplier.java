package JavaAdvanced.IntroToJava;

import java.util.Scanner;

public class CharacterMultiplier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] strings = input.split("\\s+");
        String firstString = strings[0];
        String secondString = strings[1];
        int minLen = Math.min(firstString.length(), secondString.length());
        int maxLen = Math.max(firstString.length(), secondString.length());
        int sum = 0;

        for (int i = 0; i < minLen; i++)
        {
            sum += Integer.valueOf(firstString.charAt(i)) * Integer.valueOf(secondString.charAt(i));
        }

        if (firstString.length() != secondString.length())
        {
            String longerInput;
            if (firstString.length() > secondString.length()) {
                longerInput = firstString;
            } else {
                longerInput = secondString;
            }
            for (int i = minLen; i < maxLen; i++)
            {
                sum += Integer.valueOf(longerInput.charAt(i));
            }
        }
        System.out.println(sum);
    }
}
