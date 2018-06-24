package JavaAdvanced.StringProcessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class MatchPhoneNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String regex = "\\+359([ -])2\\1\\d{3}\\1\\d{4}$";

        String name = reader.readLine();

        while (!"end".equals(name)) {
            if (Pattern.matches(regex, name)) {
                System.out.println(name);
            }
            name = reader.readLine();
        }
    }
}
