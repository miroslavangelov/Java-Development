package Algorithms.GreedyAlgorithms.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessorScheduling {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int tasksCount = Integer.parseInt(reader.readLine().split("Tasks: ")[1]);
        List<Task> tasks = new ArrayList<>();
        int maxDeadline = 0;
        List<Task> selectedTasks = new ArrayList<>();

        for (int i = 0; i < tasksCount; i++) {
            String[] taskInput = reader.readLine().split(" - ");
            int value = Integer.parseInt(taskInput[0]);
            int deadline = Integer.parseInt(taskInput[1]);
            Task task = new Task(value, deadline);
            task.setId(i+1);

            if (deadline > maxDeadline) {
                maxDeadline = deadline;
            }
            tasks.add(task);
        }

        for (int i = 0; i < maxDeadline; i++) {
            for (Task task: tasks) {
                task.setIndex(i);
            }

            int finalI = i;
            Task currentTask = tasks.stream()
                    .filter(task -> task.getDeadline() > finalI)
                    .min(Task::compareTo)
                    .orElse(null);

            selectedTasks.add(currentTask);
            tasks.remove(currentTask);
        }

        String result = "";
        int totalValue = 0;

        for (int i = 0; i < selectedTasks.size(); i++) {
            Task task = selectedTasks.get(i);

            if (i != 0) {
                result += " -> ";
            }
            result += task.getId();
            totalValue += task.getValue();
        }

        System.out.println(String.format("Optimal schedule: %s", result));
        System.out.println(String.format("Total value: %d", totalValue));
    }
}

class Task implements Comparable<Task> {
    private int id;
    private int value;
    private int deadline;
    private int index;

    public Task(int value, int deadline) {
        this.value = value;
        this.deadline = deadline;
        this.index = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public int getDeadline() {
        return deadline;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(other.value / (other.deadline - other.index), this.value / (this.deadline - this.index));
    }
}
