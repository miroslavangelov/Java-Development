package JavaAdvanced.FunctionalProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class KnightsOfHonor {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");

        for (int i = 0; i < input.length; i++) {
            String currentName = input[i];
            Consumer<String> printName = name -> System.out.println(String.format("Sir %s", name));
            printName.accept(currentName);
        }
    }
}
