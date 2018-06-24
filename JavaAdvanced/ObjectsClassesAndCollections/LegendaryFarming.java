package JavaAdvanced.ObjectsClassesAndCollections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class LegendaryFarming {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        LinkedHashMap<String, Integer> legendaryItems = new LinkedHashMap<>();
        TreeMap<String, Integer> junkItems = new TreeMap<>();
        boolean isCompleted = false;
        legendaryItems.put("shards", 0);
        legendaryItems.put("fragments", 0);
        legendaryItems.put("motes", 0);

        while (true) {
            String[] materialsInfo = reader.readLine().toLowerCase().split(" ");

            for (int i = 0; i < materialsInfo.length; i += 2) {
                int quantity = Integer.parseInt(materialsInfo[i]);
                String material = materialsInfo[i + 1];

                if (material.equals("shards")) {
                    legendaryItems.put(material, legendaryItems.get(material) + quantity);
                    if (legendaryItems.get(material) >= 250) {
                        System.out.println("Shadowmourne obtained!");
                        legendaryItems.put(material, legendaryItems.get(material) - 250);
                        isCompleted = true;
                        break;
                    }
                } else if (material.equals("fragments")) {
                    legendaryItems.put(material, legendaryItems.get(material) + quantity);
                    if (legendaryItems.get(material) >= 250) {
                        System.out.println("Valanyr obtained!");
                        legendaryItems.put(material, legendaryItems.get(material) - 250);
                        isCompleted = true;
                        break;
                    }
                } else if (material.equals("motes")) {
                    legendaryItems.put(material, legendaryItems.get(material) + quantity);
                    if (legendaryItems.get(material) >= 250) {
                        System.out.println("Dragonwrath obtained!");
                        legendaryItems.put(material, legendaryItems.get(material) - 250);
                        isCompleted = true;
                        break;
                    }
                } else {
                    if (!junkItems.containsKey(material)) {
                        junkItems.put(material, 0);
                    }
                    junkItems.put(material, junkItems.get(material) + quantity);
                }
            }

            if (isCompleted) {
                break;
            }
        }

        legendaryItems.entrySet().stream()
                .sorted((m1, m2) -> {
                    int value = m2.getValue().compareTo(m1.getValue());

                    if (value != 0){
                        return value;
                    }

                    return m1.getKey().compareTo(m2.getKey());
                })
                .forEach(mat -> System.out.println(String.format("%s: %d", mat.getKey(), mat.getValue())));
        for (Map.Entry<String, Integer> junkItem : junkItems.entrySet()) {
            System.out.println(junkItem.getKey() + ": " + junkItem.getValue());
        }
    }
}
