package JavaAdvanced.StringProcessing;

import java.util.*;

public class Palindromes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String[] words = text.split("\\W+");
        Set<String> palindromes = new TreeSet();
        for (int i = 0; i < words.length; i++) {
            if (isPalindrome(words[i])) {
                palindromes.add(words[i]);
            }
        }
        System.out.println(palindromes);
    }

    private static boolean isPalindrome(String word) {
        for (int i = 0; i < Math.floor(word.length() / 2); i++) {
            if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
                return false;
            }
        }

        return true;
    }
}
