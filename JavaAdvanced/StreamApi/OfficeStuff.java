package JavaAdvanced.StreamApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class OfficeStuff {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int linesCount = Integer.parseInt(reader.readLine());
        TreeMap<String, LinkedHashMap<String, Integer>> companies = new TreeMap<>();

        for (int i = 0; i < linesCount; i++) {
            String currentLine = reader.readLine();
            currentLine = currentLine.substring(1, currentLine.length() - 1);
            String[] companyData = currentLine.split(" - ");
            String companyName = companyData[0];
            Integer amount = Integer.parseInt(companyData[1]);
            String product = companyData[2];
            companies.putIfAbsent(companyName, new LinkedHashMap<>());
            companies.get(companyName).putIfAbsent(product, 0);
            Integer previousQuantity = companies.get(companyName).get(product);
            companies.get(companyName).put(product, previousQuantity + amount);
        }

        for (Map.Entry<String, LinkedHashMap<String, Integer>> company: companies.entrySet()){
            System.out.printf("%s: ", company.getKey());
            System.out.println(company.getValue().toString().replaceAll("[{}]", "").replaceAll("=", "-"));
        }
    }
}
