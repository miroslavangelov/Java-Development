package JavaOOPAdvanced.Generics.Tuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLineData = reader.readLine().split(" ");
        String fullName = firstLineData[0] + " " + firstLineData[1];
        String address = firstLineData[2];
        Tuple<String, String> firstTuple = new Tuple<>(fullName, address);

        String[] secondLineData = reader.readLine().split(" ");
        String name = secondLineData[0];
        int littersOfBeer = Integer.parseInt(secondLineData[1]);
        Tuple<String, Integer> secondTuple = new Tuple<>(name, littersOfBeer);

        String[] thirdLineData = reader.readLine().split(" ");
        int myInteger = Integer.parseInt(thirdLineData[0]);
        double myDouble = Double.parseDouble(thirdLineData[1]);
        Tuple<Integer, Double> thirdTuple = new Tuple<>(myInteger, myDouble);

        System.out.println(firstTuple.toString());
        System.out.println(secondTuple.toString());
        System.out.println(thirdTuple.toString());
    }
}
