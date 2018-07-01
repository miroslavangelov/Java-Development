package JavaAdvanced.FilesAndDirectories;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCount {
    private final static String wordsPath = "/src/JavaAdvanced/FilesAndDirectories/resources/words.txt";
    private final static String textPath = "/src/JavaAdvanced/FilesAndDirectories/resources/text.txt";
    private final static String outputPath = "/src/JavaAdvanced/FilesAndDirectories/resources/results.txt";
    public static void main(String[] args) throws FileNotFoundException {
        String projectPath = System.getProperty("user.dir");
        String wordsFilePath = projectPath + wordsPath;
        String textFilePath = projectPath + textPath;
        String outputFilePath = projectPath + outputPath;
        File wordsFile = new File(wordsFilePath);
        File textFile = new File(textFilePath);
        FileReader wordsReader = new FileReader(wordsFile);
        FileReader textReader = new FileReader(textFile);
        ArrayList<String> words = new ArrayList<>();
        HashMap<String, Integer> wordsCount = new HashMap<>();

        try (
                BufferedReader wordsBufferedReader = new BufferedReader(wordsReader);
                BufferedReader textBufferedReader = new BufferedReader(textReader);
                PrintWriter writer = new PrintWriter(outputFilePath, "UTF-8")
        ) {
            String wordsLine = wordsBufferedReader.readLine();
            while (wordsLine != null) {
                String[] input = wordsLine.split("\\s+");
                for (String word: input) {
                    words.add(word);
                }
                wordsLine = wordsBufferedReader.readLine();
            }

            String textLine = textBufferedReader.readLine();
            while (textLine != null) {
                for (int i = 0; i < words.size(); i++) {
                    int count = 0;
                    Pattern pattern = Pattern.compile("(?:^|\\W)" + words.get(i) + "(?:$|\\W)");
                    Matcher matcher = pattern.matcher(textLine);
                    while (matcher.find()) {
                        count++;
                    }
                    wordsCount.put(words.get(i), count);
                }
                textLine = textBufferedReader.readLine();
            }

            wordsCount.entrySet().stream()
                    .sorted((firstWord, secondWord) -> secondWord.getValue().compareTo(firstWord.getValue()))
                    .map(word -> word.getKey() + " - " + word.getValue())
                    .forEach(word -> writer.println(word));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
