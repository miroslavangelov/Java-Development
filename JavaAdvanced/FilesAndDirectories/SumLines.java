package JavaAdvanced.FilesAndDirectories;

import java.io.*;

public class SumLines {
    private final static String path = "/src/JavaAdvanced/FilesAndDirectories/resources/input.txt";
    public static void main(String[] args) throws FileNotFoundException {
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + path;
        File inputFile = new File(filePath);
        FileReader fileReader = new FileReader(inputFile);

        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();

            while (line != null) {
                long sum = 0;

                for (char c: line.toCharArray()) {
                    sum += c;
                }
                System.out.println(sum);
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
