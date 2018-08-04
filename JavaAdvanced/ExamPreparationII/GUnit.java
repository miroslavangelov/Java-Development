package JavaAdvanced.ExamPreparationII;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUnit {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        String regex = "^([A-Z][a-zA-Z0-9]+ \\| [A-Z][a-zA-Z0-9]+ \\| [A-Z][a-zA-Z0-9]+)$";
        HashMap<String, HashMap<String, ArrayList<String>>> result = new HashMap<>();

        while (!"It's testing time!".equals(currentLine)) {
            Matcher matcher = Pattern.compile(regex).matcher(currentLine);
            while (matcher.find()) {
                String[] classesData = matcher.group(1).split(" \\| ");
                String currentClass = classesData[0];
                String currentMethod = classesData[1];
                String currentTest = classesData[2];
                result.putIfAbsent(currentClass, new HashMap<>());
                result.get(currentClass).putIfAbsent(currentMethod, new ArrayList<>());
                if (!result.get(currentClass).get(currentMethod).contains(currentTest)) {
                    result.get(currentClass).get(currentMethod).add(currentTest);
                }
            }
            currentLine = reader.readLine();
        }

        result.entrySet().stream()
                .sorted(Comparator
                        .comparing((HashMap.Entry<String, HashMap<String, ArrayList<String>>> entry) ->
                                entry.getValue().values().stream().mapToInt(ArrayList::size).sum(), Comparator.reverseOrder())
                        .thenComparing((HashMap.Entry<String, HashMap<String, ArrayList<String>>> entry) -> entry.getValue().size())
                        .thenComparing(HashMap.Entry::getKey))
                .forEach(currentClass -> {
                    StringBuilder output = new StringBuilder(String.format("%s:", currentClass.getKey())).append(System.lineSeparator());
                    currentClass.getValue().entrySet().stream()
                            .sorted(Comparator
                                .comparing((HashMap.Entry<String, ArrayList<String>> entry) -> entry.getValue().size(), Comparator.reverseOrder())
                                .thenComparing(HashMap.Entry::getKey)
                            )
                            .forEach(method -> {
                                output.append(String.format("##%s", method.getKey())).append(System.lineSeparator());

                                method.getValue().stream()
                                        .sorted(Comparator
                                                .comparing(String::length)
                                                .thenComparing(Comparator.naturalOrder())
                                        ).forEach(test -> {
                                            output.append(String.format("####%s", test)).append(System.lineSeparator());
                                        });
                            });
                    System.out.print(output);
                });
    }
}
