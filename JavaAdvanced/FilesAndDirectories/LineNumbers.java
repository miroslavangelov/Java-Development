package JavaAdvanced.FilesAndDirectories;

import java.io.*;

public class LineNumbers {
    private final static String inputPath = "/src/JavaAdvanced/FilesAndDirectories/resources/inputLineNumbers.txt";
    private final static String outputPath = "/src/JavaAdvanced/FilesAndDirectories/resources/lineNumbersOutput.txt";
    public static void main(String[] args) throws FileNotFoundException {
        String projectPath = System.getProperty("user.dir");
        String inputFilePath = projectPath + inputPath;
        String outputFilePath = projectPath + outputPath;
        File inputFile = new File(inputFilePath);
        FileReader fileReader = new FileReader(inputFile);

        try (BufferedReader reader = new BufferedReader(fileReader); PrintWriter writer = new PrintWriter(outputFilePath, "UTF-8")) {
            String line = reader.readLine();
            int linesCount = 1;

            while (line != null) {
                writer.println(String.format("%d. %s", linesCount, line));
                linesCount += 1;
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
