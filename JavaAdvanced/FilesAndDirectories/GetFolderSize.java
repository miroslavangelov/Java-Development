package JavaAdvanced.FilesAndDirectories;

import java.io.*;

public class GetFolderSize {
    private final static String outputPath = "/src/JavaAdvanced/FilesAndDirectories/resources/getFolderSizeOutput.txt";
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        String outputFilePath = projectPath + outputPath;
        File projectFolder = new File(projectPath);
        long sum;
        sum = getFolderSize(projectFolder);

        try (PrintWriter writer = new PrintWriter(outputFilePath, "UTF-8")) {
            writer.println(String.format("Folder size: %d", sum));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long getFolderSize(File directory) {
        long length = 0;

        for (File file : directory.listFiles()) {
            if (file.isFile()) {
                length += file.length();
            } else {
                length += getFolderSize(file);
            }
        }

        return length;
    }
}
