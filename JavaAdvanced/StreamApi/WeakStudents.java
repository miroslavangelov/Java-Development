package JavaAdvanced.StreamApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class WeakStudents {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentStudent = reader.readLine();
        LinkedHashMap<String, List<String>> students = new LinkedHashMap<>();

        while (!currentStudent.equals("END")) {
            String[] input = currentStudent.split(" ");
            List<String> grades = new ArrayList<>();
            for (int i = 2; i < input.length; i++) {
                grades.add(input[i]);
            }
            students.put(input[0] + " " + input[1], grades);
            currentStudent = reader.readLine();
        }

        students.entrySet().stream()
                .filter(student -> student.getValue().stream().filter(grade -> grade.equals("2") || grade.equals("3")).count() >= 2)
                .map(student -> student.getKey())
                .forEach(student -> System.out.println(student));
    }
}
