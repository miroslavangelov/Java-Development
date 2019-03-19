package JavaAdvanced.FilesAndDirectories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateZipArchive {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir") + "/src/JavaAdvanced/FilesAndDirectories/resources/";

        try (
                FileInputStream fisFirst = new FileInputStream(new File(projectPath + "text.txt"));
                FileInputStream fisSecond = new FileInputStream(new File(projectPath + "words.txt"));
                FileInputStream fisThird = new FileInputStream(new File(projectPath + "results.txt"));
                ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(projectPath + "files.zip"))
        ) {
            int byteContainer;
            zos.putNextEntry(new ZipEntry("text.txt"));

            while ((byteContainer = fisFirst.read()) != -1) {
                zos.write(byteContainer);
            }
            zos.closeEntry();
            zos.putNextEntry(new ZipEntry("words.txt"));

            while ((byteContainer = fisSecond.read()) != -1) {
                zos.write(byteContainer);
            }
            zos.closeEntry();
            zos.putNextEntry(new ZipEntry("results.txt"));

            while ((byteContainer = fisThird.read()) != -1) {
                zos.write(byteContainer);
            }
            zos.closeEntry();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
