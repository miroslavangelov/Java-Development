package JavaAdvanced.StreamApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupByGroup {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        ArrayList<Person> students = new ArrayList<>();

        while(!"END".equals(line)) {
            String[] studentData = line.split(" ");
            String studentName = studentData[0] + " " + studentData[1];
            Integer studentGroup = Integer.parseInt(studentData[2]);
            Person student = new Person(studentName, studentGroup);
            students.add(student);
            line = reader.readLine();
        }

        Map<Integer, List<String>> result =
            students.stream()
                .collect(
                    Collectors.groupingBy(
                            Person::getGroup,
                            Collectors.mapping(Person::getName,Collectors.toList())
                    )
                );

        result.entrySet()
            .forEach(group -> System.out.println(group.getKey() + " - " + String.join(", ", group.getValue())));
    }

    public static class Person {
        private String name;
        private Integer group;

        public Person(String name, Integer group) {
            this.name = name;
            this.group = group;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getGroup() {
            return group;
        }

        public void setGroup(int group) {
            this.group = group;
        }
    }
}
