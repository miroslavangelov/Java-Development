package JavaAdvanced.DataRepresentationAndManipulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MultiplyBigNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String bigNumber = reader.readLine().replaceFirst("^0+(?!$)", "");
        int multiplier = Integer.parseInt(reader.readLine());
        StringBuilder result = new StringBuilder();
        int remainder = 0;
        for (int i = bigNumber.length() - 1; i >= 0; i--) {
            int currentDigit = Integer.parseInt(String.valueOf(bigNumber.charAt(i)));
            int product = currentDigit * multiplier + remainder;
            result.insert(0, product % 10);
            remainder = product / 10;
        }

        if (remainder > 0) {
            result.insert(0, remainder);
        }

        System.out.println(result.toString().replaceFirst("^0+(?!$)", ""));
    }
}
