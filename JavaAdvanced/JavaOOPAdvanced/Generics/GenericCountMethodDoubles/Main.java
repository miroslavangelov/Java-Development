package JavaOOPAdvanced.Generics.GenericCountMethodDoubles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int lines = Integer.parseInt(reader.readLine());
        List<Double> list = new ArrayList<>();
        GenericBox<Double> genericBox = new GenericBox<>();

        for (int i = 0; i < lines; i++) {
            Double currentDouble = Double.parseDouble(reader.readLine());
            list.add(currentDouble);
        }
        Double comparableElement = Double.parseDouble(reader.readLine());
        System.out.println(genericBox.getGreaterElementsCount(list, comparableElement));
    }
}
