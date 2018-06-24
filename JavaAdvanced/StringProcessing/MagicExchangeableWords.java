package JavaAdvanced.StringProcessing;

import java.util.HashMap;
import java.util.Scanner;

public class MagicExchangeableWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split(" ");
        String firstWord = words[0];
        String secondWord = words[1];
        HashMap<Character, Character> map = new HashMap<>();
        boolean isExchangeable = true;
        int minLength = Math.min(firstWord.length(), secondWord.length());

        for (int i = 0; i < minLength; i++) {
            char firstChar = firstWord.charAt(i);
            char secondChar = secondWord.charAt(i);

            if (!map.containsKey(firstChar)) {
                if (map.containsValue(secondChar)) {
                    isExchangeable = false;
                    break;
                }
                map.put(firstChar, secondChar);
            } else {
                if (map.get(firstChar) != secondChar) {
                    isExchangeable = false;
                    break;
                }
            }
        }

        String substr = "";

        if (firstWord.length() > secondWord.length()) {
            substr = firstWord.substring(minLength);
        } else {
            substr = secondWord.substring(minLength);
        }

        for (int i = 0; i < substr.length(); i++) {
            char substrChar = substr.charAt(i);
            if (!map.containsValue(substrChar) && !map.containsKey(substrChar)) {
                isExchangeable = false;
            }
        }

        System.out.println(isExchangeable);
    }
}
