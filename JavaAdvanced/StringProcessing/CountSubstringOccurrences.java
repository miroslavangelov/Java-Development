package JavaAdvanced.StringProcessing;

import java.util.Scanner;

public class CountSubstringOccurrences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine().toLowerCase();
        String findSubstr = scanner.nextLine().toLowerCase();
        int count = 0;
        int index = text.indexOf(findSubstr);
        while(index != -1)
        {
            index = text.indexOf(findSubstr, index + 1);
            count++;
        }
        System.out.println(count);
    }
}
