package JavaAdvanced.StreamApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

public class EnrolledStudents {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentStudent = reader.readLine();
        LinkedHashMap<String, String> students = new LinkedHashMap<>();

        while (!currentStudent.equals("END")) {
            String[] input = currentStudent.split(" ");
            StringBuilder grades = new StringBuilder();
            for (int i = 1; i < input.length; i++) {
                grades.append(input[i] + " ");
            }
            students.put(input[0], grades.toString());
            currentStudent = reader.readLine();
        }

        students.entrySet().stream()
                .filter(student -> student.getKey().substring(student.getKey().length() - 2).equals("14") || student.getKey().substring(student.getKey().length() - 2).equals("15"))
                .map(student -> student.getValue())
                .forEach(student -> System.out.println(student));
    }
}
