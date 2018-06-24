package JavaAdvanced.StreamApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StudentsByAge {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentStudent = reader.readLine();
        List<String> students = new ArrayList<>();

        while (!currentStudent.equals("END")) {
            students.add(currentStudent);
            currentStudent = reader.readLine();
        }

        students.stream()
                .map(student -> student.split(" "))
                .filter(student -> Integer.parseInt(student[2]) <= 24 && Integer.parseInt(student[2]) >= 18)
                .map(student -> student[0] + " " + student[1] + " " + student[2])
                .forEach(student -> System.out.println(student));
    }
}
