package JavaAdvanced.FunctionalProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PredicateForNames {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nameLength = Integer.parseInt(reader.readLine());
        String[] names = reader.readLine().split(" ");
        Predicate<String> checkNameLength = name -> name.length() <= nameLength;
        Consumer<String> print = System.out::println;
        Arrays.stream(names)
                .filter(checkNameLength)
                .forEach(print);
    }
}
