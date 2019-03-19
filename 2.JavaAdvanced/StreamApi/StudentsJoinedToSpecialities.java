package JavaAdvanced.StreamApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class StudentsJoinedToSpecialities {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        ArrayList<StudentSpecialty> studentSpecialties = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();

        while (!"Students:".equals(line)) {
            String[] specialitiesData = line.split(" ");
            StudentSpecialty studentSpecialty = new StudentSpecialty(
                specialitiesData[0] + " " + specialitiesData[1],
                Integer.parseInt(specialitiesData[2])
            );
            studentSpecialties.add(studentSpecialty);
            line = reader.readLine();
        }
        line = reader.readLine();
        while (!"END".equals(line)) {
            String[] studentsData = line.split(" ");
            Student student = new Student(
                    studentsData[1] + " " + studentsData[2],
                    Integer.parseInt(studentsData[0])
            );
            students.add(student);
            line = reader.readLine();
        }

        students.stream().sorted(Comparator.comparing(Student::getStudentName))
            .forEach(student -> studentSpecialties.stream()
                .filter(studentSpecialty -> studentSpecialty.facultyNumber.equals(student.facultyNumber))
                    .forEach(studentSpecialty -> System.out.println(String.format("%s %d %s", student.getStudentName(), studentSpecialty.getFacultyNumber(), studentSpecialty.getSpecialtyName()))));
    }

    public static class StudentSpecialty {
        private String specialtyName;
        private Integer facultyNumber;

        StudentSpecialty(String specialtyName, Integer facultyNumber) {
            this.specialtyName = specialtyName;
            this.facultyNumber = facultyNumber;
        }

        public String getSpecialtyName() {
            return specialtyName;
        }

        public void setSpecialtyName(String specialtyName) {
            this.specialtyName = specialtyName;
        }

        public Integer getFacultyNumber() {
            return facultyNumber;
        }

        public void setFacultyNumber(Integer facultyNumber) {
            this.facultyNumber = facultyNumber;
        }
    }

    public static class Student {
        private String studentName;
        private Integer facultyNumber;

        Student(String studentName, Integer facultyNumber) {
            this.studentName = studentName;
            this.facultyNumber = facultyNumber;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public Integer getFacultyNumber() {
            return facultyNumber;
        }

        public void setFacultyNumber(Integer facultyNumber) {
            this.facultyNumber = facultyNumber;
        }
    }
}
