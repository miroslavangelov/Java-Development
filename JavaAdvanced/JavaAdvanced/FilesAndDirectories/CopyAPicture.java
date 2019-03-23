package JavaAdvanced.FilesAndDirectories;

import java.io.*;

public class CopyAPicture {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        byte[] buffer = new byte[1024];
        try (
            InputStream inputStream = new FileInputStream(new File(projectPath + "/src/JavaAdvanced/FilesAndDirectories/resources/picture.jpg"));
            OutputStream outputStream = new FileOutputStream(new File(projectPath + "/src/JavaAdvanced/FilesAndDirectories/resources/picture-copy.jpg"))
        ) {
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
