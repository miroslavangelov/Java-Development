package JavaAdvanced.StreamApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LittleJohn {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String arrowsRegex = "(>-{5}>)|(>>-{5}>)|(>>>-{5}>>)";
        int largeArrowsCount = 0;
        int mediumArrowsCount = 0;
        int smallArrowsCount = 0;

        for (int i = 0; i < 4; i++) {
            String line = reader.readLine();
            Matcher arrowsMatcher = Pattern.compile(arrowsRegex).matcher(line);
            while(arrowsMatcher.find()) {
                if (arrowsMatcher.group(1) != null) {
                    smallArrowsCount += 1;
                }
                if (arrowsMatcher.group(2) != null) {
                    mediumArrowsCount += 1;
                }
                if (arrowsMatcher.group(3) != null) {
                    largeArrowsCount += 1;
                }
            }
        }
        int arrowsNumber = Integer.parseInt(Integer.toString(smallArrowsCount) + Integer.toString(mediumArrowsCount) + Integer.toString(largeArrowsCount));
        String arrowsBinary = Integer.toBinaryString(arrowsNumber) + new StringBuilder(Integer.toBinaryString(arrowsNumber)).reverse().toString();
        int result = Integer.parseInt(arrowsBinary, 2);
        System.out.println(result);
    }
}
