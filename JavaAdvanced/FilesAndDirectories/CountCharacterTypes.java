package JavaAdvanced.FilesAndDirectories;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class CountCharacterTypes {
    private final static String inputPath = "/src/JavaAdvanced/FilesAndDirectories/resources/input.txt";
    private final static String outputPath = "/src/JavaAdvanced/FilesAndDirectories/resources/countCharacterTypesOutput.txt";
    public static void main(String[] args) throws FileNotFoundException {
        String projectPath = System.getProperty("user.dir");
        String inputFilePath = projectPath + inputPath;
        String outputFilePath = projectPath + outputPath;
        File inputFile = new File(inputFilePath);
        FileReader fileReader = new FileReader(inputFile);
        ArrayList<Character> vowelsList = new ArrayList<>();
        Collections.addAll(vowelsList, 'a', 'e', 'o', 'u', 'i');
        ArrayList<Character> punctuationList = new ArrayList<>();
        Collections.addAll(punctuationList, '.', ',', '!', '?');

        try (BufferedReader reader = new BufferedReader(fileReader); PrintWriter writer = new PrintWriter(outputFilePath, "UTF-8")) {
            String line = reader.readLine();
            int vowelsCount = 0;
            int consonantsCount = 0;
            int punctuationCount = 0;

            while (line != null) {
                for (char currentChar: line.toCharArray()) {
                    if (currentChar == ' ') {
                        continue;
                    } else if (vowelsList.contains(currentChar)) {
                        vowelsCount += 1;
                    } else if (punctuationList.contains(currentChar)) {
                        punctuationCount += 1;
                    } else {
                        consonantsCount += 1;
                    }
                }
                line = reader.readLine();
            }
            writer.println(String.format("Vowels: %d", vowelsCount));
            writer.println(String.format("Consonants: %d", consonantsCount));
            writer.println(String.format("Punctuation: %d", punctuationCount));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
