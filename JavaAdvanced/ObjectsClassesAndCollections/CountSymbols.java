package JavaAdvanced.ObjectsClassesAndCollections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class CountSymbols {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char[] input = reader.readLine().toCharArray();
        Map<Character, Integer> charsCount = new TreeMap<>();

        for (char currentChar : input) {
            if (!charsCount.containsKey(currentChar)) {
                charsCount.put(currentChar, 0);
            }

            charsCount.put(currentChar, charsCount.get(currentChar) + 1);
        }

        for (Map.Entry<Character, Integer> entry : charsCount.entrySet()) {
            System.out.println(String.format("%s: %s time/s", entry.getKey(), entry.getValue()));
        }
    }
}
