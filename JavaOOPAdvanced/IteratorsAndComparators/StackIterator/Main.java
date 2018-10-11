package JavaOOPAdvanced.IteratorsAndComparators.StackIterator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        StackIterator stackIterator = new StackIterator();

        while(!"END".equals(currentLine)) {
            String[] data = currentLine.split(" ", 2);
            String command = data[0];

            switch (command) {
                case "Push":
                    stackIterator.push(data[1].split(", "));
                    break;
                case "Pop":
                    try {
                        stackIterator.pop();
                    } catch (IllegalArgumentException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
            }

            currentLine = reader.readLine();
        }
        stackIterator.forEach(System.out::println);
        stackIterator.forEach(System.out::println);
    }
}
