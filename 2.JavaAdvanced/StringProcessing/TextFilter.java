package JavaAdvanced.StringProcessing;

import java.util.Scanner;

public class TextFilter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] bannedWords = scanner.nextLine().split(", ");
        String text = scanner.nextLine();

        for (int i = 0; i < bannedWords.length; i++) {
            String currentWord = bannedWords[i];
            String replacement = new String(new char[currentWord.length()]).replace('\0', '*');
            text = text.replaceAll(currentWord, replacement);
        }

        System.out.println(text);
    }
}
