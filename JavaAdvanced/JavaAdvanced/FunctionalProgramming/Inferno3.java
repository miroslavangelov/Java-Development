package JavaAdvanced.FunctionalProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.function.Consumer;


public class Inferno3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        ArrayList<Integer> gems = new ArrayList<>();
        LinkedHashSet<String> filters = new LinkedHashSet<>();
        ArrayList<Integer> gemsForRemove = new ArrayList<>();

        for (int i = 0; i < input.length; i++) {
            gems.add(Integer.parseInt(input[i]));
        }

        String line = reader.readLine();
        while (!"Forge".equals(line)) {
            String[] commands = line.split(";", 2);
            String command = commands[0];
            String filter = commands[1];

            switch (command) {
                case "Exclude":
                    filters.add(filter);
                    break;
                case "Reverse":
                    if (filters.contains(filter)) {
                        filters.remove(filter);
                    }
                    break;
            }

            line = reader.readLine();
        }

        for (String filter: filters) {
            String[] currentFilter = filter.split(";");
            String filterType = currentFilter[0];
            int filterValue = Integer.parseInt(currentFilter[1]);
            gemsForRemove.addAll(excludeGems(gems, filterType, filterValue));
        }

        for (Integer gem: gemsForRemove) {
            if (gems.contains(gem)) {
                gems.remove(gem);
            }
        }
        Consumer<String> printName = System.out::println;
        printName.accept(Arrays.toString(gems.toArray()).replaceAll("[\\[\\],]", ""));
    }

    private static ArrayList<Integer> excludeGems(ArrayList<Integer> gems, String filterType, int filterValue) {
        ArrayList<Integer> filteredGems = new ArrayList<>();

        switch(filterType) {
            case "Sum Left":
                for (int i = 0; i < gems.size(); i++) {
                    int currentGem = gems.get(i);
                    int leftGem = (i - 1 >= 0) ? gems.get(i - 1) : 0;

                    if (currentGem + leftGem == filterValue) {
                        filteredGems.add(currentGem);
                    }
                }
                break;
            case "Sum Right":
                for (int i = 0; i < gems.size(); i++) {
                    int currentGem = gems.get(i);
                    int rightGem = (i + 1 < gems.size() - 1) ? gems.get(i + 1) : 0;

                    if (currentGem + rightGem == filterValue) {
                        filteredGems.add(currentGem);
                    }
                }
                break;
            case "Sum Left Right":
                for (int i = 0; i < gems.size(); i++) {
                    int currentGem = gems.get(i);
                    int rightGem = (i + 1 < gems.size() - 1) ? gems.get(i + 1) : 0;
                    int leftGem = (i - 1 >= 0) ? gems.get(i - 1) : 0;

                    if (currentGem + rightGem + leftGem == filterValue) {
                        filteredGems.add(currentGem);
                    }
                }
                break;
        }

        return filteredGems;
    }
}
