package DataStructures.HashTables.Exercise;

import DataStructures.HashTables.Lab.HashTable;
import DataStructures.HashTables.Lab.KeyValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CountSymbols {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine();
        HashTable<Character, Integer> symbols = new HashTable<>();

        for (int i = 0; i < text.length(); i++) {
            Character currentChar = text.charAt(i);

            if (symbols.containsKey(currentChar)) {
                symbols.addOrReplace(currentChar, symbols.get(currentChar) + 1);
            } else {
                symbols.add(currentChar, 1);
            }
        }

        for (KeyValue<Character, Integer> symbol : symbols.sortPairs()) {
            System.out.println(String.format("%s: %d time/s", symbol.getKey(), symbol.getValue()));
        }
    }
}
