package JavaAdvanced.StreamApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FilterStudentsByPhone {
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
                .filter(student -> (student[2].indexOf("02") == 0 || student[2].indexOf("+3592") == 0))
                .map(student -> student[0] + " " + student[1])
                .forEach(student -> System.out.println(student));
    }
}
