package JavaAdvanced.FunctionalProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.BiPredicate;

public class PredicateParty {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<String> guestList = new LinkedList<>(Arrays.asList(reader.readLine().split("\\s+")));
        String command = reader.readLine();
        BiPredicate<String, String> startsWith = (name, prefix) -> name.startsWith(prefix);
        BiPredicate<String, String> endsWith = (name, suffix) -> name.endsWith(suffix);
        BiPredicate<String, String> checkLength = (name, length) -> name.length() == Integer.parseInt(length);

        while (!command.equals("Party!")) {
            String[] commands = command.split(" ");
            String operation = commands[0];
            String criteria = commands[1];
            String condition = commands[2];
            switch (criteria) {
                case "StartsWith":
                    forEachName(guestList, operation, condition, startsWith);break;
                case "EndsWith":
                    forEachName(guestList, operation, condition, endsWith);break;
                case "Length":
                    forEachName(guestList, operation, condition, checkLength);break;
            }
            command = reader.readLine();
        }

        System.out.println(
                guestList.size() > 0 ?
                        String.format("%s are going to the party!", guestList.toString().replaceAll("[\\[\\]]", "")) :
                        "Nobody is going to the party!"
        );
    }

    private static void forEachName(LinkedList<String> guestList, String operation, String condition, BiPredicate<String, String> conditionAsPredicate) {
        for (int i = guestList.size() - 1; i >= 0; i--) {
            String currentGuest = guestList.get(i);
            if (conditionAsPredicate.test(currentGuest, condition)) {
                switch (operation) {
                    case "Double":
                        guestList.add(i, currentGuest);
                        break;
                    case "Remove":
                        guestList.remove(i);
                }
            }
        }
    }
}
