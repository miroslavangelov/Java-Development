package JavaOOPAdvanced.ObjectCommunicationAndEvents.Problem7_1984;

import JavaOOPAdvanced.ObjectCommunicationAndEvents.Problem7_1984.interfaces.Subject;
import JavaOOPAdvanced.ObjectCommunicationAndEvents.Problem7_1984.models.*;
import JavaOOPAdvanced.ObjectCommunicationAndEvents.Problem7_1984.models.Observer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] numbersData = reader.readLine().split("\\s+");
        int numberOfEntities = Integer.parseInt(numbersData[0]);
        int numberOfInstitutions = Integer.parseInt(numbersData[1]);
        int numberOfChanges = Integer.parseInt(numbersData[2]);
        List<BaseEntity> entities = new ArrayList<>();

        for (int i = 0; i < numberOfEntities; i++) {
            String[] entitiesData = reader.readLine().split("\\s+");
            String entityType = entitiesData[0];

            switch (entityType) {
                case "Employee":
                    String employeeId = entitiesData[1];
                    String employeeName = entitiesData[2];
                    int employeeIncome = Integer.parseInt(entitiesData[3]);
                    BaseEntity employee = new Employee(employeeId, employeeName, employeeIncome);
                    entities.add(employee);
                    break;
                case "Company":
                    String companyId = entitiesData[1];
                    String companyName = entitiesData[2];
                    int companyTurnover = Integer.parseInt(entitiesData[3]);
                    int companyRevenue = Integer.parseInt(entitiesData[4]);
                    BaseEntity company = new Company(companyId, companyName, companyTurnover, companyRevenue);
                    entities.add(company);
                    break;
            }
        }

        List<Observer> observers = new ArrayList<>();

        for (int i = 0; i < numberOfInstitutions; i++) {
            String[] institutionsData = reader.readLine().split("\\s+");
            Set<Subject> subjectSet = new HashSet<>();

            for (int j = 3; j < institutionsData.length; j++) {
                int finalJ = j;
                entities.stream()
                        .filter(s ->
                                Arrays.stream(s.getClass()
                                        .getDeclaredFields())
                                        .anyMatch(fName -> institutionsData[finalJ]
                                                .equals(fName.getName()))
                                        || Arrays.stream(s.getClass()
                                        .getSuperclass()
                                        .getDeclaredFields())
                                        .anyMatch(fName -> institutionsData[finalJ]
                                                .equals(fName.getName())))
                        .forEach(subjectSet::add);
            }

            String institutionId = institutionsData[1];
            String institutionName = institutionsData[2];
            String[] monitoredSubjects = Arrays.stream(institutionsData)
                    .skip(3)
                    .toArray(String[]::new);
            Observer institution = new Institution(institutionId, institutionName, monitoredSubjects, subjectSet.toArray(new Subject[0]));
            observers.add(institution);
        }

        for (int i = 0; i < numberOfChanges; i++) {
            String[] changesData = reader.readLine().split("\\s+");

            for (BaseEntity entity : entities) {
                if (entity.getId().equals(changesData[0])) {
                    Method[] methodsSuper = entity.getClass()
                            .getSuperclass()
                            .getDeclaredMethods();

                    Method[] methodsClass = entity.getClass()
                            .getDeclaredMethods();

                    List<Method> methods = new ArrayList<>();
                    methods.addAll(Arrays.asList(methodsSuper));
                    methods.addAll(Arrays.asList(methodsClass));

                    for (Method method : methods) {
                        method.setAccessible(true);

                        String methodName = method.getName()
                                .toLowerCase();

                        if (methodName.startsWith("set")
                                && methodName.endsWith(changesData[1].toLowerCase())) {
                            method.invoke(entity, changesData[2]);
                            break;
                        }
                    }
                }
            }
        }

        observers.forEach(System.out::println);
    }
}
