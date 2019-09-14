package Algorithms.ProblemSolvingMethodology.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RectangleIntersection {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int rectanglesCount = Integer.parseInt(reader.readLine());
        int[][] plane = new int[2000][2000];

        for (int i = 0; i < rectanglesCount; i++) {
            String[] rectangleData = reader.readLine().split(" ");
            int x1 = Integer.parseInt(rectangleData[0]) + 1000;
            int x2 = Integer.parseInt(rectangleData[1]) + 1000;
            int y1 = Integer.parseInt(rectangleData[2]) + 1000;
            int y2 = Integer.parseInt(rectangleData[3]) + 1000;

            for (int j = x1; j < x2; j++) {
                for (int k = y1; k < y2; k++) {
                    plane[j][k]++;
                }
            }
        }

        int totalIntersectionArea = 0;
        for (int i = 0; i < plane.length; i++) {
            for (int j = 0; j < plane.length; j++) {
                if (plane[i][j] > 1) {
                    totalIntersectionArea++;
                }
            }
        }
        System.out.println(totalIntersectionArea);
    }
}
