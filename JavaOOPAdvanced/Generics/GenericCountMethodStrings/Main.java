package JavaOOPAdvanced.Generics.GenericCountMethodStrings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int lines = Integer.parseInt(reader.readLine());
        List<String> list = new ArrayList<>();
        GenericBox<String> genericBox = new GenericBox<>();

        for (int i = 0; i < lines; i++) {
            String currentLine = reader.readLine();
            list.add(currentLine);
        }
        String comparableElement = reader.readLine();
        System.out.println(genericBox.getGreaterElementsCount(list, comparableElement));
    }
}
