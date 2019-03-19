package JavaAdvanced.Abstraction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ToTheStars {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Star[] stars = new Star[3];

        for (int i = 0; i < 3; i++) {
            String[] input = reader.readLine().split(" ");
            String starName = input[0].toLowerCase();
            double x = Double.parseDouble(input[1]);
            double y = Double.parseDouble(input[2]);
            stars[i] = new Star(starName, x, y);
        }
        String[] startPosition = reader.readLine().split(" ");
        double x = Double.parseDouble(startPosition[0]);
        double y = Double.parseDouble(startPosition[1]);
        int turns = Integer.parseInt(reader.readLine());

        for (int i = 0; i <= turns; i++) {
            boolean isInSpace = true;

            for (Star star: stars) {
                if (star.getX() - 1 <= x && star.getX() + 1 >= x && star.getY() - 1 <= y && star.getY() + 1 >= y) {
                    System.out.println(star.getName());
                    isInSpace = false;
                }
            }

            if (isInSpace) {
                System.out.println("space");
            }
            y++;
        }
    }

    static class Star {
        String name;
        double x;
        double y;

        public Star(String name, double x, double y) {
            this.name = name.toLowerCase();
            this.x = x;
            this.y = y;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name.toLowerCase();
        }

        public double getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
