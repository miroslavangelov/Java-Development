package Algorithms.SolvingPracticalProblemsPartII.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FastAndFurious {
    private static double[][] graph;
    private static Map<String, Integer> locationIndices = new HashMap<>();
    private static Map<String, List<Record>> records = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        int locationIndex = 0;
        List<String[]> lines = new ArrayList<>();

        while (!"Records:".equals(input = reader.readLine())) {
            String[] roadsData = input.split(" ");
            String firstLocation = roadsData[0];
            String secondLocation = roadsData[1];
            if (!locationIndices.containsKey(firstLocation)) {
                locationIndices.put(firstLocation, locationIndex++);
            }
            if (!locationIndices.containsKey(secondLocation)) {
                locationIndices.put(secondLocation, locationIndex++);
            }
            lines.add(roadsData);
        }

        graph = new double[locationIndices.size()][locationIndices.size()];
        for (String[] line: lines) {
            String firstLocation = line[0];
            String secondLocation = line[1];
            double distance = Double.parseDouble(line[2]);
            double maxSpeedAllowed = Double.parseDouble(line[3]);
            double minTime = (distance / maxSpeedAllowed) * 3600;
            int firstIndex = locationIndices.get(firstLocation);
            int secondIndex = locationIndices.get(secondLocation);
            graph[firstIndex][secondIndex] = minTime;
            graph[secondIndex][firstIndex] = minTime;
        }

        Set<String> speeders = new HashSet<>();
        while (!"End".equals(input = reader.readLine())) {
            String[] recordsData = input.split(" ");
            String licencePlate = recordsData[1];

            if (speeders.contains(licencePlate)) {
                continue;
            }
            String location = recordsData[0];
            String time = recordsData[2];
            int destinationIndex = locationIndices.get(location);
            int totalSeconds = getSeconds(time);

            if (records.containsKey(licencePlate)) {
                for (Record record: records.get(licencePlate)) {
                    boolean isSpeeding = checkIfCarIsSpeeding(destinationIndex, totalSeconds, record);

                    if (isSpeeding) {
                        speeders.add(licencePlate);
                        break;
                    }
                }
            }
            records.putIfAbsent(licencePlate, new ArrayList<>());
            records.get(licencePlate).add(new Record(destinationIndex, totalSeconds));
        }

        StringBuilder sb = new StringBuilder();
        speeders.stream()
                .sorted(Comparator.naturalOrder())
                .forEach(licensePlate -> sb.append(licensePlate).append(System.lineSeparator()));
        System.out.print(sb);
    }

    private static boolean checkIfCarIsSpeeding(int destinationIndex, int totalSeconds, Record record) {
        int previousSeconds = record.getTime();
        int timeTravelled = Math.abs(totalSeconds - previousSeconds);
        int startIndex = record.getTownIndex();
        double timeAllowed = dijkstra(startIndex, destinationIndex);

        return timeTravelled < timeAllowed;
    }

    private static double dijkstra(int startIndex, int destinationIndex) {
        double[] time = new double[graph.length];
        for (int i = 0; i < time.length; i++) {
            time[i] = Double.MAX_VALUE;
        }
        time[startIndex] = 0D;

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(i -> time[i]));
        queue.offer(startIndex);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            for (int i = 0; i < graph.length; i++) {
                double distanceToChild = graph[vertex][i];
                double totalDistance = time[vertex] + distanceToChild;

                if (distanceToChild != 0D && time[i] > totalDistance) {
                    time[i] = totalDistance;
                    queue.remove(i);
                    queue.offer(i);

                }
            }
        }

        return time[destinationIndex] != Double.MAX_VALUE ? time[destinationIndex] : 0D;
    }

    private static int getSeconds(String time) {
        String[] timeData = time.split(":");
        int hours = Integer.parseInt(timeData[0]);
        int minutes = Integer.parseInt(timeData[1]);
        int seconds = Integer.parseInt(timeData[2]);

        return hours * 3600 + minutes * 60 + seconds;
    }
}

class Record {
    private int townIndex;
    private int time;

    Record (int townIndex, int time) {
        this.townIndex = townIndex;
        this.time = time;
    }


    public int getTownIndex() {
        return townIndex;
    }

    public void setTownIndex(int townIndex) {
        this.townIndex = townIndex;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
