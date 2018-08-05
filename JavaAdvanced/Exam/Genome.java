package JavaAdvanced.Exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Genome {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        String genomeRegex = "^([a-z!@#$?]+)=([0-9]*)--([0-9]*)<<([a-z]+)$";
        HashMap<String, Integer> result = new HashMap<>();

        while (!"Stop!".equals(currentLine)) {
            Matcher genomeMatcher = Pattern.compile(genomeRegex).matcher(currentLine);
            while (genomeMatcher.find()) {
                String genomeName = genomeMatcher.group(1);
                int nameLength = Integer.parseInt(genomeMatcher.group(2));
                int genomeNameLength = 0;

                for (int i = 0; i < genomeName.length(); i++) {
                    if (Character.isLetter(genomeName.charAt(i))){
                        genomeNameLength += 1;
                    }
                }

                if (nameLength == genomeNameLength) {
                    int genesCount = Integer.parseInt(genomeMatcher.group(3));
                    String organism = genomeMatcher.group(4);
                    result.putIfAbsent(organism, 0);
                    result.put(organism, result.get(organism) + genesCount);
                }
            }

            currentLine = reader.readLine();
        }

        result.entrySet().stream()
                .sorted((firstOrganism, secondOrganism) -> secondOrganism.getValue().compareTo(firstOrganism.getValue()))
                .forEach(organism -> System.out.println(String.format("%s has genome size of %d", organism.getKey(), organism.getValue())));
    }
}
