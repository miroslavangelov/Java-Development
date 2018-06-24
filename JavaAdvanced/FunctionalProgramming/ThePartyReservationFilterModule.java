package JavaAdvanced.FunctionalProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import java.util.LinkedList;
import java.util.function.BiPredicate;

public class ThePartyReservationFilterModule {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<String> guestList = new LinkedList<>(Arrays.asList(reader.readLine().split("\\s+")));
        String filter = reader.readLine();
        LinkedList<String> filters = new LinkedList<>();
        BiPredicate<String, String> startsWith = (name, prefix) -> name.startsWith(prefix);
        BiPredicate<String, String> endsWith = (name, suffix) -> name.endsWith(suffix);
        BiPredicate<String, String> checkLength = (name, length) -> name.length() == Integer.parseInt(length);
        BiPredicate<String, String> contains = (name, string) -> name.contains(string);

        while (!filter.equals("Print")) {
            String input[] = filter.split(";");
            String instruction = input[0];
            String filterType = input[1];
            String filterValue = input[2];
            getFilters(filters, instruction, filterType, filterValue);

            filter = reader.readLine();
        }

        for (int i = 0; i < filters.size(); i++) {
            String[] currentFilter = filters.get(i).split(":");
            String filterType = currentFilter[0];
            String filterValue = currentFilter[1];

            switch(filterType) {
                case "Starts with":
                    forEachName(guestList, filterValue, startsWith);break;
                case "Ends with":
                    forEachName(guestList, filterValue, endsWith);break;
                case "Length":
                    forEachName(guestList, filterValue, checkLength);break;
                case "Contains":
                    forEachName(guestList, filterValue, contains);break;
            }
        }

        System.out.println(String.format("%s", guestList.toString().replaceAll("[\\[\\],]", "")));
    }

    private static void forEachName(LinkedList<String> guestList, String filterValue, BiPredicate<String, String> condition) {
        for (int i = guestList.size() - 1; i >= 0; i--) {
            String currentGuest = guestList.get(i);

            if (condition.test(currentGuest, filterValue)) {
                guestList.remove(i);
            }
        }
    }

    private static void getFilters(LinkedList<String> filters, String instruction, String filterType, String filterValue) {
        String filter = String.format("%s:%s", filterType, filterValue);

        switch (instruction) {
            case "Add filter":
                if (!filters.contains(filter)) {
                    filters.add(filter);
                }
                break;
            case "Remove filter":
                if (filters.contains(filter)) {
                    filters.remove(filter);
                }
                break;
        }
    }
}
