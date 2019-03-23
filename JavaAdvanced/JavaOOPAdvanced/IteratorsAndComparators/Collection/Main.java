package JavaOOPAdvanced.IteratorsAndComparators.Collection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] createData = reader.readLine().split(" ");
        ListyIterator<String> listyIterator = new ListyIterator<>(Arrays.stream(createData).skip(1).toArray(String[]::new));

        String currentLine = reader.readLine();

        while (!"END".equals(currentLine)) {
            switch (currentLine) {
                case "Move":
                    System.out.println(listyIterator.move());
                    break;
                case "HasNext":
                    System.out.println(listyIterator.hasNext());
                    break;
                case "Print":
                    listyIterator.print();
                    break;
                case "PrintAll":
                    listyIterator.printAll();
                    break;
            }

            currentLine = reader.readLine();
        }
    }
}
