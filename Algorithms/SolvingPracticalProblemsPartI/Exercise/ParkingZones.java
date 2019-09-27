package Algorithms.SolvingPracticalProblemsPartI.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ParkingZones {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int parkingZonesCount = Integer.parseInt(reader.readLine());
        List<ParkingZone> parkingZones = new ArrayList<>();

        for (int i = 0; i < parkingZonesCount; i++) {
            String[] parkingZoneData = reader.readLine().split(" ");
            String name = parkingZoneData[0].split(":")[0];
            int x = Integer.parseInt(parkingZoneData[1].split(",")[0]);
            int y = Integer.parseInt(parkingZoneData[2].split(",")[0]);
            int width = x + Integer.parseInt(parkingZoneData[3].split(",")[0]) - 1;
            int height = y + Integer.parseInt(parkingZoneData[4].split(",")[0]) - 1;
            double pricePerMinute = Double.parseDouble(parkingZoneData[5]);
            ParkingZone parkingZone = new ParkingZone(name, x, y, width, height, pricePerMinute);
            parkingZones.add(parkingZone);
        }

        List<FreeParkingLot> freeParkingLots = new ArrayList<>();
        String[] freeParkingLotsData = reader.readLine().split("; ");
        for (int i = 0; i < freeParkingLotsData.length; i++) {
            String[] lotData = freeParkingLotsData[i].split(", ");
            int x = Integer.parseInt(lotData[0]);
            int y = Integer.parseInt(lotData[1]);
            FreeParkingLot freeParkingLot = new FreeParkingLot(i, x, y);
            for (ParkingZone parkingZone: parkingZones) {
                if (x >= parkingZone.getX() &&
                    x <= parkingZone.getWidth() &&
                    y >= parkingZone.getY() &&
                    y <= parkingZone.getHeight()) {
                    freeParkingLot.setParkingZone(parkingZone);
                    break;
                }
            }
            freeParkingLots.add(freeParkingLot);
        }

        String[] targetPointData = reader.readLine().split(", ");
        int targetPointX = Integer.parseInt(targetPointData[0]);
        int targetPointY = Integer.parseInt(targetPointData[1]);
        int timeToTraverse = Integer.parseInt(reader.readLine());
        int minMoves = Integer.MAX_VALUE;
        FreeParkingLot bestParkingLot = freeParkingLots.get(0);

        for (FreeParkingLot freeParkingLot: freeParkingLots) {
            int totalDistance = -1;

            totalDistance += Math.abs(freeParkingLot.getY() - targetPointY);
            totalDistance += Math.abs(targetPointX - freeParkingLot.getX());

            if (totalDistance <= minMoves &&
                freeParkingLot.getParkingZone().getPricePerMinute() <= bestParkingLot.getParkingZone().getPricePerMinute()) {
                minMoves = totalDistance;
                bestParkingLot = freeParkingLot;
            }
        }

        double totalTime = Math.ceil((minMoves * 2 * timeToTraverse) / 60.0);
        double cost = totalTime * bestParkingLot.getParkingZone().getPricePerMinute();
        System.out.println(String.format(
                "Zone Type: %s; X: %d; Y: %d; Price: %.2f",
                bestParkingLot.getParkingZone().getName(),
                bestParkingLot.getX(),
                bestParkingLot.getY(),
                cost));
    }

    private static class ParkingZone {
        private String name;
        private int x;
        private int y;
        private int width;
        private int height;
        private double pricePerMinute;

        ParkingZone (String name, int x, int y, int width, int height, double pricePerMinute) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.pricePerMinute = pricePerMinute;
        }

        public String getName() {
            return name;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public double getPricePerMinute() {
            return pricePerMinute;
        }
    }

    private static class FreeParkingLot {
        private int id;
        private int x;
        private int y;
        private ParkingZone parkingZone;

        FreeParkingLot (int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        public int getId() {
            return id;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public ParkingZone getParkingZone() {
            return parkingZone;
        }

        public void setParkingZone(ParkingZone parkingZone) {
            this.parkingZone = parkingZone;
        }
    }
}
