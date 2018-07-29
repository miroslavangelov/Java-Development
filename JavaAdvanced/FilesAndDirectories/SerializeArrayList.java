package JavaAdvanced.FilesAndDirectories;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class SerializeArrayList {
    public static void main(String[] args) throws FileNotFoundException {
        String outputPath = "/src/JavaAdvanced/FilesAndDirectories/resources/list.ser";
        String projectPath = System.getProperty("user.dir");
        String outputFilePath = projectPath + outputPath;
        ArrayList<Double> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, 2.7, 3.4, 2.6);

        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(arrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fileInputStream = new FileInputStream(outputFilePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            ArrayList<Double> loadedArrayList = (ArrayList<Double>) objectInputStream.readObject();
            System.out.println(loadedArrayList.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
