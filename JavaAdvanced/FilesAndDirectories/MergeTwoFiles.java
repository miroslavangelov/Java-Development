package JavaAdvanced.FilesAndDirectories;

import java.io.*;

public class MergeTwoFiles {
    private final static String firstPath = "/src/JavaAdvanced/FilesAndDirectories/resources/inputOne.txt";
    private final static String secondPath = "/src/JavaAdvanced/FilesAndDirectories/resources/inputTwo.txt";
    private final static String outputPath = "/src/JavaAdvanced/FilesAndDirectories/resources/mergeTwoFilesOutput.txt";
    public static void main(String[] args) throws FileNotFoundException {
        String projectPath = System.getProperty("user.dir");
        String firstFilePath = projectPath + firstPath;
        String secondFilePath = projectPath + secondPath;
        String outputFilePath = projectPath + outputPath;
        File firstFile = new File(firstFilePath);
        File secondFile = new File(secondFilePath);
        FileReader firstFileReader = new FileReader(firstFile);
        FileReader secondFileReader = new FileReader(secondFile);

        try (
                BufferedReader firstFileBufferedReader = new BufferedReader(firstFileReader);
                BufferedReader secondFileBufferedReader = new BufferedReader(secondFileReader);
                PrintWriter writer = new PrintWriter(outputFilePath, "UTF-8")
        ) {
            String firstFileLine = firstFileBufferedReader.readLine();
            while (firstFileLine != null) {
                writer.println(firstFileLine);
                firstFileLine = firstFileBufferedReader.readLine();
            }

            String secondFileLine = secondFileBufferedReader.readLine();
            while (secondFileLine != null) {
                writer.println(secondFileLine);
                secondFileLine = secondFileBufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
