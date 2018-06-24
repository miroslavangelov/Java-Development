package JavaAdvanced.ObjectsClassesAndCollections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DragonArmy {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int dragonsCount = Integer.parseInt(reader.readLine());
        LinkedHashMap<String, TreeMap<String, ArrayList<Integer>>> dragonTypesData = new LinkedHashMap<>();

        for (int i = 0; i < dragonsCount; i++) {
            String[] currentDragon = reader.readLine().split(" ");
            String dragonType = currentDragon[0];
            String dragonName = currentDragon[1];
            int damage = currentDragon[2].equals("null") ? 45 : Integer.parseInt(currentDragon[2]);
            int health = currentDragon[3].equals("null") ? 250 : Integer.parseInt(currentDragon[3]);
            int armor = currentDragon[4].equals("null") ? 10 : Integer.parseInt(currentDragon[4]);

            TreeMap<String,  ArrayList<Integer>> dragonsData = new TreeMap<>();
            ArrayList<Integer> currentDragonStats = new ArrayList<>();

            currentDragonStats.addAll(Arrays.asList(damage, health, armor));
            dragonsData.put(dragonName, currentDragonStats);

            if (!dragonTypesData.containsKey(dragonType)) {
                dragonTypesData.put(dragonType, dragonsData);
            }
            dragonTypesData.get(dragonType).putAll(dragonsData);
        }

        LinkedHashMap<String, ArrayList<Double>> dragonTypeAverageStats = new LinkedHashMap<>();
        for (Map.Entry<String, TreeMap<String, ArrayList<Integer>>> dragonType: dragonTypesData.entrySet()) {
            int totalHealth = 0;
            int totalArmor = 0;
            int totalDamage = 0;
            double averageHealth;
            double averageArmor;
            double averageDamage;

            for (Map.Entry<String, ArrayList<Integer>> dragon: dragonType.getValue().entrySet()) {
                totalDamage += dragon.getValue().get(0);
                totalHealth += dragon.getValue().get(1);
                totalArmor += dragon.getValue().get(2);
            }
            averageHealth = (double)totalHealth / dragonType.getValue().size();
            averageArmor = (double)totalArmor / dragonType.getValue().size();
            averageDamage = (double)totalDamage / dragonType.getValue().size();
            ArrayList<Double> currentDragonTypeAverageStats = new ArrayList<>();
            currentDragonTypeAverageStats.addAll(Arrays.asList(averageDamage, averageHealth, averageArmor));
            dragonTypeAverageStats.put(dragonType.getKey(), currentDragonTypeAverageStats);
        }

        for (Map.Entry<String, ArrayList<Double>> dragonType: dragonTypeAverageStats.entrySet()) {
            System.out.println(String.format("%s::(%.2f/%.2f/%.2f)", dragonType.getKey(), dragonType.getValue().get(0), dragonType.getValue().get(1), dragonType.getValue().get(2)));
            for (Map.Entry<String, ArrayList<Integer>> dragon: dragonTypesData.get(dragonType.getKey()).entrySet()) {
                System.out.println(String.format("-%s -> damage: %d, health: %d, armor: %d", dragon.getKey(), dragon.getValue().get(0), dragon.getValue().get(1), dragon.getValue().get(2)));
            }
        }
    }
}
