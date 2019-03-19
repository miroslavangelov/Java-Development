package JavaOOPAdvanced.ObjectCommunicationAndEvents.WorkForce;

import JavaOOPAdvanced.ObjectCommunicationAndEvents.WorkForce.employees.PartTimeEmployee;
import JavaOOPAdvanced.ObjectCommunicationAndEvents.WorkForce.employees.StandardEmployee;
import JavaOOPAdvanced.ObjectCommunicationAndEvents.WorkForce.interfaces.Employee;
import JavaOOPAdvanced.ObjectCommunicationAndEvents.WorkForce.jobs.Job;
import JavaOOPAdvanced.ObjectCommunicationAndEvents.WorkForce.observers.Subject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        Map<String, Employee> employees = new HashMap<>();
        List<Job> jobs = new ArrayList<>();
        Subject subject = new Subject();

        while(!"End".equals(currentLine)) {
            String[] data = currentLine.split("\\s+");
            String command = data[0];

            switch (command) {
                case "StandartEmployee":
                    String employeeName = data[1];
                    Employee standardEmployee = new StandardEmployee(employeeName);
                    employees.putIfAbsent(employeeName, standardEmployee);
                    break;
                case "PartTimeEmployee":
                    employeeName = data[1];
                    Employee partTimeEmployee = new PartTimeEmployee(employeeName);
                    employees.putIfAbsent(employeeName, partTimeEmployee);
                    break;
                case "Pass":
                    subject.notifyAllObservers();
                    for (int i = 0; i < jobs.size(); i++) {
                        Job currentJob = jobs.get(i);

                        if (currentJob.getHoursOfWorkRequired() <= 0) {
                            System.out.println(String.format("Job %s done!", currentJob.getName()));
                            subject.remove(currentJob);
                            jobs.remove(currentJob);
                        }
                    }
                    break;
                case "Job":
                    String jobName = data[1];
                    int hoursOfWorkRequired = Integer.parseInt(data[2]);
                    employeeName = data[3];
                    Employee employee = employees.get(employeeName);
                    Job job = new Job(jobName, hoursOfWorkRequired, employee, subject);
                    jobs.add(job);
                    subject.attach(job);
                    break;
                case "Status":
                    for (Job currentJob : jobs) {
                        System.out.println(currentJob.toString());
                    }
                    break;
            }

            currentLine = reader.readLine();
        }
    }
}
