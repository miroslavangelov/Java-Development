package JavaAdvanced.StringProcessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MelrahShake {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder input = new StringBuilder(reader.readLine());
        StringBuilder pattern = new StringBuilder(reader.readLine());

        while(true) {
            int firstIndex = input.indexOf(pattern + "");
            int lastIndex = input.lastIndexOf(pattern + "");

            if (firstIndex == lastIndex || (pattern + "").equals("")) {
                System.out.println("No shake.");
                break;
            }

            input = input.replace(firstIndex, firstIndex + pattern.length(), "");
            input = input.replace(lastIndex - pattern.length(), lastIndex, "");

            int indexForRemove = pattern.length() / 2;
            pattern.replace(indexForRemove, indexForRemove + 1, "");
            System.out.println("Shaked it.");
        }

        System.out.println(input);
    }
}
