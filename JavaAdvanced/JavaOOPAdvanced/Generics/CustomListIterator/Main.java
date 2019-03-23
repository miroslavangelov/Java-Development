package JavaOOPAdvanced.Generics.CustomListIterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        CustomList<String> customList = new CustomList<>();

        while (!"END".equals(currentLine)) {
            String[] data = currentLine.split(" ");
            String command = data[0];

            switch (command) {
                case "Add":
                    String element = data[1];
                    customList.add(element);
                    break;
                case "Remove":
                    int index = Integer.parseInt(data[1]);
                    customList.remove(index);
                    break;
                case "Contains":
                    element = data[1];
                    System.out.println(customList.contains(element));
                    break;
                case "Swap":
                    int i = Integer.parseInt(data[1]);
                    int j = Integer.parseInt(data[2]);
                    customList.swap(i, j);
                    break;
                case "Greater":
                    element = data[1];
                    System.out.println(customList.countGreaterThan(element));
                    break;
                case "Max":
                    System.out.println(customList.getMax());
                    break;
                case "Min":
                    System.out.println(customList.getMin());
                    break;
                case "Print":
                    System.out.print(customList.toString());
                    break;
                case "Sort":
                    Sorter.sort(customList);
                    break;
            }
            currentLine = reader.readLine();
        }
    }
}
