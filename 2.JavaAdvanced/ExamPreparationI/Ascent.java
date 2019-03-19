package JavaAdvanced.ExamPreparationI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ascent {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        String regex = "((_|,)([A-Za-z]+)([0-9]))";
        LinkedHashMap<String, String> foundPatterns = new LinkedHashMap<>();

        while(!"Ascend".equals(currentLine)) {
            for (Map.Entry<String, String> pattern: foundPatterns.entrySet()) {
                currentLine = currentLine.replaceAll(pattern.getKey(), pattern.getValue());
            }
            Matcher matcher = Pattern.compile(regex).matcher(currentLine);
            while (matcher.find()) {
                char[] message = matcher.group(3).toCharArray();
                int digit = Integer.parseInt(matcher.group(4));

                for (int i = 0; i < message.length; i++) {
                    int ascii;
                    if (matcher.group(2).equals(",")) {
                        ascii = (int)message[i] + digit;
                    } else {
                        ascii = (int)message[i] - digit;
                    }
                    message[i] = (char)ascii;
                }
                currentLine = currentLine.replaceAll(matcher.group(1), String.valueOf(message));
                foundPatterns.put(matcher.group(1), String.valueOf(message));
            }
            System.out.println(currentLine);
            currentLine = reader.readLine();
        }
    }
}
