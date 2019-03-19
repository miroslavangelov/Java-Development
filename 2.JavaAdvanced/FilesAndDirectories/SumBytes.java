package JavaAdvanced.FilesAndDirectories;

import java.io.*;

public class SumBytes {
    private final static String path = "/src/JavaAdvanced/FilesAndDirectories/resources/input.txt";
    public static void main(String[] args) throws FileNotFoundException {
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + path;
        File inputFile = new File(filePath);
        FileReader fileReader = new FileReader(inputFile);

        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            long sum = 0;
            while (line != null) {
                for (char c: line.toCharArray()) {
                    sum += c;
                }
                line = reader.readLine();
            }
            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
