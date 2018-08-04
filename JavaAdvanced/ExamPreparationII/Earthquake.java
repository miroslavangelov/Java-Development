package JavaAdvanced.ExamPreparationII;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Earthquake {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int activitiesCount = Integer.parseInt(reader.readLine());
        Deque<Deque<Integer>> queue = new ArrayDeque<>();
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < activitiesCount; i++) {
            String[] currentLine = reader.readLine().split(" ");
            Deque<Integer> waves = new ArrayDeque<>();
            for (int j = 0; j < currentLine.length; j++) {
                waves.add(Integer.valueOf(currentLine[j]));
            }
            queue.add(waves);
        }

        while (queue.size() > 0) {
            Deque<Integer> currentActivity = queue.remove();
            int seismicity = currentActivity.remove();

            result.add(seismicity);
            while (currentActivity.size() > 0) {
                int wave = currentActivity.peek();
                if (wave <= seismicity) {
                    currentActivity.remove();
                } else {
                    break;
                }
            }

            if (currentActivity.size() > 0) {
                queue.add(currentActivity);
            }
        }
        System.out.println(result.size());
        System.out.println(Arrays.toString(result.toArray()).replaceAll("[\\[\\],]", ""));
    }
}
