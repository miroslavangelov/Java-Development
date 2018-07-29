package JavaAdvanced.FilesAndDirectories;

import java.io.*;

public class SerializeCustomObject {
    public static void main(String[] args) {
        String outputPath = "/src/JavaAdvanced/FilesAndDirectories/resources/course.ser";
        String projectPath = System.getProperty("user.dir");
        String outputFilePath = projectPath + outputPath;
        Course course = new Course("Java Development", 5);

        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(course);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fileInputStream = new FileInputStream(outputFilePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            Course loadedCourse = (Course) objectInputStream.readObject();
            System.out.println(loadedCourse.getName() + " has " + loadedCourse.students + " students.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static class Course implements Serializable {
        String name;
        int students;

        public Course(String name,int students) {
            this.name = name;
            this.students = students;
        }

        public String getName() {
            return name;
        }
        public int getStudnets() {
            return students;
        }
    }
}
