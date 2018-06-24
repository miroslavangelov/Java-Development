package JavaAdvanced.ObjectsClassesAndCollections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TruckTour {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int pumpsCount = Integer.parseInt(reader.readLine());
        Deque<long[]> track = new ArrayDeque<>();
        List<long[]> list = new ArrayList<>();

        for (int i = 0; i < pumpsCount; i++) {
            long[] currentPump = Arrays.stream(reader.readLine().split(" ")).mapToLong(Integer::parseInt).toArray();
            track.add(currentPump);
        }
        list.addAll(track);

        for (int i = 0; i < list.size(); i++) {
            long totalFuel = 0;
            boolean isCompleted = true;
            for (long[] currentPump: track) {
                long fuel = currentPump[0];
                long distance = currentPump[1];
                totalFuel += fuel;
                if (totalFuel < distance) {
                    isCompleted = false;
                    break;
                }
                totalFuel -= distance;
            }

            if (isCompleted) {
                System.out.println(i);
                return;
            }

            long[] currentElement = list.get(i);
            track.remove();
            track.add(currentElement);
        }

        System.out.println(-1);
    }
}
