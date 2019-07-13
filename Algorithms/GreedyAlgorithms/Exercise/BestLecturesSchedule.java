package Algorithms.GreedyAlgorithms.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BestLecturesSchedule {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int lecturesCount = Integer.parseInt(reader.readLine().split(": ")[1]);
        List<Lecture> lectures = new ArrayList<>();

        for (int i = 0; i < lecturesCount; i++) {
            String[] lectureData = reader.readLine().split(": ");
            String lectureName = lectureData[0];
            int lectureStartTime = Integer.parseInt(lectureData[1].split(" - ")[0]);
            int lectureEndTime = Integer.parseInt(lectureData[1].split(" - ")[1]);
            Lecture lecture = new Lecture(lectureName, lectureStartTime, lectureEndTime);
            lectures.add(lecture);
        }

        List<Lecture> selectedLectures = new ArrayList<>();

        while (lectures.size() > 0) {
            lectures.sort(Comparator.naturalOrder());
            Lecture currentLecture = lectures.get(0);
            selectedLectures.add(currentLecture);
            List<Lecture> lecturesToRemove = new ArrayList<>();

            for (Lecture lecture : lectures) {
                if (lecture.getStartTime() < currentLecture.getEndTime()) {
                    lecturesToRemove.add(lecture);
                }
            }
            lectures.removeAll(lecturesToRemove);
        }

        StringBuilder result = new StringBuilder();

        result.append(String.format("Lectures (%d):", selectedLectures.size()))
            .append(System.lineSeparator());
        for (Lecture selectedLecture : selectedLectures) {
            result.append(String.format("%d-%d -> %s", selectedLecture.getStartTime(), selectedLecture.getEndTime(), selectedLecture.getName()))
                    .append(System.lineSeparator());
        }
        System.out.println(result.toString());
    }
}

class Lecture implements Comparable<Lecture> {
    private String name;
    private int startTime;
    private int endTime;

    public Lecture(String name, int startTime, int endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    @Override
    public int compareTo(Lecture other) {
        return Integer.compare(this.endTime, other.endTime);
    }
}