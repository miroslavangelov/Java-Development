package Algorithms.SolvingPracticalProblemsPartI.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Elections {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int seatsCount = Integer.parseInt(reader.readLine());
        int partiesCount = Integer.parseInt(reader.readLine());
        int[] partiesSeats = new int[partiesCount];
        int maxSeats = 0;

        for (int i = 0; i < partiesCount; i++) {
            partiesSeats[i] = Integer.parseInt(reader.readLine());
            maxSeats += partiesSeats[i];
        }

        BigInteger[] sums = new BigInteger[maxSeats + 1];
        sums[0] = BigInteger.ONE;

        for (int seat: partiesSeats) {
            for (int i = sums.length - 1; i >= 0; i--) {
                if (sums[i] != null && sums[i].intValue() != 0) {
                    BigInteger currentSum = sums[i + seat] == null ? BigInteger.ZERO : sums[i + seat];
                    sums[i + seat] = currentSum.add(sums[i]);
                }
            }
        }

        BigInteger count = BigInteger.ZERO;
        for (int i = seatsCount; i < sums.length; i++) {
            BigInteger currentSum = sums[i] == null ? BigInteger.ZERO : sums[i];
            count = count.add(currentSum);
        }
        System.out.println(count);
    }
}
