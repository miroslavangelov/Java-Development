package JavaAdvanced.FilesAndDirectories;

import java.io.*;

public class AllCapitals {
    private final static String inputPath = "/src/JavaAdvanced/FilesAndDirectories/resources/input.txt";
    private final static String outputPath = "/src/JavaAdvanced/FilesAndDirectories/resources/allCapitalsOutput.txt";
    public static void main(String[] args) throws FileNotFoundException {
        String projectPath = System.getProperty("user.dir");
        String inputFilePath = projectPath + inputPath;
        String outputFilePath = projectPath + outputPath;
        File inputFile = new File(inputFilePath);
        FileReader fileReader = new FileReader(inputFile);

        try (BufferedReader reader = new BufferedReader(fileReader);PrintWriter writer = new PrintWriter(outputFilePath, "UTF-8")) {
            String line = reader.readLine();
            while (line != null) {
                writer.println(line.toUpperCase());
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
