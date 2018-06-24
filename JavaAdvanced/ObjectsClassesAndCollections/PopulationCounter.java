package JavaAdvanced.ObjectsClassesAndCollections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PopulationCounter {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        LinkedHashMap<String, HashMap<String, Long>> countriesData = new LinkedHashMap<>();
        while (!input.equals("report")) {
            String[] data = input.split("\\|");
            String city = data[0];
            String country = data[1];
            long population = Long.parseLong(data[2]);
            LinkedHashMap<String, Long> citiesData = new LinkedHashMap<>();
            citiesData.put(city, population);
            if (!countriesData.containsKey(country)) {
                countriesData.put(country, citiesData);
            }
            countriesData.get(country).putAll(citiesData);
            input = reader.readLine();
        }

        LinkedHashMap<String, Long> countriesByPopulation = new LinkedHashMap<>();
        for (Map.Entry<String, HashMap<String, Long>> country: countriesData.entrySet()) {
            long totalPopulation = 0;
            for (Map.Entry<String, Long> city : country.getValue().entrySet()) {
                totalPopulation += city.getValue();
            }
            countriesByPopulation.put(country.getKey(), totalPopulation);
        }
        Map<String, Long> countriesSorted = new LinkedHashMap<>();
        countriesByPopulation.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEachOrdered(x -> countriesSorted.put(x.getKey(), x.getValue()));

        for (String country : countriesSorted.keySet()) {
            System.out.println(country + " (total population: " + countriesSorted.get(country) + ")");

            countriesData.get(country).entrySet().stream()
                    .sorted(Map.Entry.comparingByValue((v1, v2) -> v2.compareTo(v1)))
                    .forEach(entry -> {
                        System.out.println("=>" + entry.getKey() + ": " + entry.getValue());
                    });
        }
    }
}
