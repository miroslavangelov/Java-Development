package hell;

import hell.entities.heroes.Assassin;
import hell.entities.heroes.Barbarian;
import hell.entities.heroes.Wizard;
import hell.entities.items.CommonItem;
import hell.entities.items.RecipeItem;
import hell.entities.miscellaneous.HeroInventory;
import hell.interfaces.*;
import hell.io.ConsoleReader;
import hell.io.ConsoleWriter;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        OutputWriter outputWriter = new ConsoleWriter();
        InputReader inputReader = new ConsoleReader();
        Map<String, Hero> heroes = new LinkedHashMap<>();

        while (true) {
            String[] input = inputReader.readLine().split("\\s+");
            String command = input[0];

            switch (command) {
                case "Hero":
                    String heroName = input[1];
                    String heroType = input[2];
                    Hero hero = null;

                    switch (heroType) {
                        case "Barbarian":
                            hero = new Barbarian(heroName, new HeroInventory());
                            break;
                        case "Assassin":
                            hero = new Assassin(heroName, new HeroInventory());
                            break;
                        case "Wizard":
                            hero = new Wizard(heroName, new HeroInventory());
                            break;
                    }
                    heroes.putIfAbsent(hero.getName(), hero);
                    outputWriter.writeLine(String.format("Created %s - %s", hero.getClass().getSimpleName(), hero.getName()));
                    break;
                case "Item":
                    String itemName = input[1];
                    heroName = input[2];
                    int strengthBonus = Integer.parseInt(input[3]);
                    int agilityBonus = Integer.parseInt(input[4]);
                    int intelligenceBonus = Integer.parseInt(input[5]);
                    int hitPointsBonus = Integer.parseInt(input[6]);
                    int damageBonus = Integer.parseInt(input[7]);
                    Item commonItem = new CommonItem(itemName, strengthBonus, agilityBonus, intelligenceBonus, hitPointsBonus, damageBonus);

                    hero = heroes.get(heroName);
                    hero.addItem(commonItem);
                    outputWriter.writeLine(String.format("Added item - %s to Hero - %s", commonItem.getName(), hero.getName()));
                    break;
                case "Recipe":
                    itemName = input[1];
                    heroName = input[2];
                    strengthBonus = Integer.parseInt(input[3]);
                    agilityBonus = Integer.parseInt(input[4]);
                    intelligenceBonus = Integer.parseInt(input[5]);
                    hitPointsBonus = Integer.parseInt(input[6]);
                    damageBonus = Integer.parseInt(input[7]);
                    List<String> requiredItems = new ArrayList<>();

                    requiredItems.addAll(Arrays.asList(input).subList(8, input.length));

                    Recipe recipeItem = new RecipeItem(itemName, strengthBonus, agilityBonus, intelligenceBonus, hitPointsBonus, damageBonus, requiredItems);
                    hero = heroes.get(heroName);
                    hero.addRecipe(recipeItem);
                    outputWriter.writeLine(String.format("Added recipe - %s to Hero - %s", recipeItem.getName(), hero.getName()));
                    break;
                case "Inspect":
                    heroName = input[1];
                    hero = heroes.get(heroName);
                    outputWriter.writeLine(hero.toString());
                    break;
                case "Quit":
                    final int[] index = {1};
                    heroes.values().stream()
                            .sorted((firstHero, secondHero) -> {
                                long firstComparatorSumFirstHero = firstHero.getStrength() + firstHero.getAgility() + firstHero.getIntelligence();
                                long firstComparatorSumSecondHero = secondHero.getStrength() + secondHero.getAgility() + secondHero.getIntelligence();
                                int comparatorByFirstSum = Long.compare(firstComparatorSumSecondHero, firstComparatorSumFirstHero);

                                if (comparatorByFirstSum != 0) {
                                    return comparatorByFirstSum;
                                }

                                long secondComparatorSumFirstHero = firstHero.getHitPoints() + firstHero.getDamage();
                                long secondComparatorSumSecondHero = secondHero.getHitPoints() + secondHero.getDamage();

                                return Long.compare(secondComparatorSumSecondHero, secondComparatorSumFirstHero);
                            })
                            .forEach(currentHero -> {
                                StringBuilder result = new StringBuilder();

                                result.append(String.format("%d. %s: %s", index[0]++, currentHero.getClass().getSimpleName(), currentHero.getName()))
                                        .append(System.lineSeparator())
                                        .append(String.format("###HitPoints: %d", currentHero.getHitPoints()))
                                        .append(System.lineSeparator())
                                        .append(String.format("###Damage: %d", currentHero.getDamage()))
                                        .append(System.lineSeparator())
                                        .append(String.format("###Strength: %d", currentHero.getStrength()))
                                        .append(System.lineSeparator())
                                        .append(String.format("###Agility: %d", currentHero.getAgility()))
                                        .append(System.lineSeparator())
                                        .append(String.format("###Intelligence: %d", currentHero.getIntelligence()))
                                        .append(System.lineSeparator());
                                if (currentHero.getItems().size() > 0) {
                                    result.append("###Items: ").append(currentHero.getItems().stream().map(Item::getName).collect(Collectors.joining(", ")));
                                } else {
                                    result.append("###Items: None");
                                }

                                outputWriter.writeLine(result.toString());
                            });
                    return;
            }
        }
    }
}