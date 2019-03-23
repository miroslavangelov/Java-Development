package JavaAdvanced.ExamPreparationII;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DHARMASupplies {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        ArrayList<String> islandParts = new ArrayList();
        int islandPartsCount = 0;
        int cratesCount = 0;
        String cratesRegex = "\\[.*?]";

        while (!"Collect".equals(currentLine)) {
            Matcher cratesMatcher = Pattern.compile(cratesRegex).matcher(currentLine);
            while (cratesMatcher.find()) {
                cratesCount += 1;
            }
            islandParts.add(currentLine);
            islandPartsCount += 1;
            currentLine = reader.readLine();
        }
        int validSupplyCreatesDigit = cratesCount / islandPartsCount;
        int totalFoodSuppliesCount = 0;
        int totalDrinksSuppliesCount = 0;
        int supplyCratesCount = 0;
        String foodRegex = String.format("\\[#[0-9]{%d}([A-Za-z0-9\\s]+)#([0-9]{%d})]", validSupplyCreatesDigit, validSupplyCreatesDigit);
        String drinksRegex = String.format("\\[#[a-z]{%d}([A-Za-z0-9\\s]+)#([a-z]{%d})]", validSupplyCreatesDigit, validSupplyCreatesDigit);

        for (int i = 0; i < islandParts.size(); i++) {
            String currentIslandPart = islandParts.get(i);
            Matcher foodMatcher = Pattern.compile(foodRegex).matcher(currentIslandPart);
            Matcher drinksMatcher = Pattern.compile(drinksRegex).matcher(currentIslandPart);

            while (foodMatcher.find()) {
                int sumOfUniqueCharacters = Arrays.stream(foodMatcher.group(1).split("")).distinct().mapToInt(s -> s.charAt(0)).sum();
                int lengthOfSupplyTag = foodMatcher.group(2).length();

                totalFoodSuppliesCount += sumOfUniqueCharacters * lengthOfSupplyTag;
                supplyCratesCount += 1;
            }
            while (drinksMatcher.find()) {
                int sumOfCharactersBody = Arrays.stream(drinksMatcher.group(1).split("")).mapToInt(s -> s.charAt(0)).sum();
                int sumOfCharactersTag = Arrays.stream(drinksMatcher.group(2).split("")).mapToInt(s -> s.charAt(0)).sum();

                totalDrinksSuppliesCount += sumOfCharactersBody * sumOfCharactersTag;;
                supplyCratesCount += 1;
            }
        }

        if (supplyCratesCount > 0) {
            System.out.println(String.format("Number of supply crates: %d", supplyCratesCount));
            System.out.println(String.format("Amount of food collected: %d", totalFoodSuppliesCount));
            System.out.println(String.format("Amount of drinks collected: %d", totalDrinksSuppliesCount));
        } else {
            System.out.println("No supplies found!");
        }

    }
}
