package JavaOOPAdvanced.Generics.GenericBoxOfInteger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int lines = Integer.parseInt(reader.readLine());

        for (int i = 0; i < lines; i++) {
            int currentInteger = Integer.parseInt(reader.readLine());
            GenericBox<Integer> box = new GenericBox<>(currentInteger);
            System.out.print(box.toString());
        }
    }
}
