package JavaOOPAdvanced.Generics.Threeuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLineData = reader.readLine().split(" ");
        String fullName = firstLineData[0] + " " + firstLineData[1];
        String address = firstLineData[2];
        String town = firstLineData[3];
        Threeuple<String, String, String> firstTuple = new Threeuple<>(fullName, address, town);

        String[] secondLineData = reader.readLine().split(" ");
        String name = secondLineData[0];
        int littersOfBeer = Integer.parseInt(secondLineData[1]);
        boolean isDrunk = "drunk".equals(secondLineData[2]);
        Threeuple<String, Integer, Boolean> secondTuple = new Threeuple<>(name, littersOfBeer, isDrunk);

        String[] thirdLineData = reader.readLine().split(" ");
        String anotherName = thirdLineData[0];
        double accountBalance = Double.parseDouble(thirdLineData[1]);
        String bankName = thirdLineData[2];
        Threeuple<String, Double, String> thirdTuple = new Threeuple<>(anotherName, accountBalance, bankName);

        System.out.println(firstTuple.toString());
        System.out.println(secondTuple.toString());
        System.out.println(thirdTuple.toString());
    }
}
