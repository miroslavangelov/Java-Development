package JavaAdvanced.IntroToJava;

import java.util.Scanner;

public class TriangleArea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String a = scanner.nextLine();
        String[] parametersForA = a.split(" ");
        int ax = Integer.parseInt(parametersForA[0]);
        int ay = Integer.parseInt(parametersForA[1]);
        String b = scanner.nextLine();
        String[] parametersForB = b.split(" ");
        int bx = Integer.parseInt(parametersForB[0]);
        int by = Integer.parseInt(parametersForB[1]);
        String c = scanner.nextLine();
        String[] parametersForC = c.split(" ");
        int cx = Integer.parseInt(parametersForC[0]);
        int cy = Integer.parseInt(parametersForC[1]);
        int area = ((ax * (by - cy)) + (bx * (cy - ay)) + (cx * (ay - by))) / 2;
        if (area < 0) {
            area = -area;
        }
        System.out.println(area);
    }
}
