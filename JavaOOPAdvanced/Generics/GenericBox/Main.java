package JavaOOPAdvanced.Generics.GenericBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int lines = Integer.parseInt(reader.readLine());

        for (int i = 0; i < lines; i++) {
            String currentLine = reader.readLine();
            GenericBox<String> box = new GenericBox<>(currentLine);
            System.out.print(box.toString());
        }
    }
}
