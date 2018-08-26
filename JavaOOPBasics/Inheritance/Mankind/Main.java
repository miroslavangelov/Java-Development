package JavaOOPBasics.Inheritance.Mankind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] studentData = reader.readLine().split(" ");
            String studentFirstName = studentData[0];
            String studentLastName = studentData[1];
            String studentFacultyNumber = studentData[2];
            Student student = new Student(studentFirstName, studentLastName, studentFacultyNumber);
            System.out.println(student.toString());
            String[] workerData = reader.readLine().split(" ");
            String workerFirstName = workerData[0];
            String workerLastName = workerData[1];
            double workerWeekSalary = Double.parseDouble(workerData[2]);
            double workerWorkingHours = Double.parseDouble(workerData[3]);
            Worker worker = new Worker(workerFirstName, workerLastName, workerWeekSalary, workerWorkingHours);
            System.out.println(worker.toString());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
