package JavaOOPAdvanced.Generics.GenericSwapMethodIntegers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int lines = Integer.parseInt(reader.readLine());
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < lines; i++) {
            int currentInteger = Integer.parseInt(reader.readLine());
            list.add(currentInteger);
        }
        GenericSwap<Integer> genericSwap = new GenericSwap<>(list);
        String[] indexesData = reader.readLine().split(" ");
        int i = Integer.parseInt(indexesData[0]);
        int j = Integer.parseInt(indexesData[1]);
        genericSwap.swap(i, j);
        System.out.print(genericSwap.toString());
    }
}
