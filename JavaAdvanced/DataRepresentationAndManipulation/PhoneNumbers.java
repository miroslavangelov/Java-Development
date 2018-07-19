package JavaAdvanced.DataRepresentationAndManipulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pairsRegex = "([A-Z][A-Za-z]*)[^A-Za-z\\+0-9]*?(\\+?[0-9][0-9\\/\\- ().]*[0-9])";
        StringBuilder text = new StringBuilder();
        String line = reader.readLine();

        while (!line.equals("END")) {
            text.append(line);
            line = reader.readLine();
        }

        Matcher pairsMatcher = Pattern.compile(pairsRegex).matcher(text);
        StringBuilder result = new StringBuilder();
        boolean isMatchFound = false;

        result.append("<ol>");
        while (pairsMatcher.find()) {
            isMatchFound = true;
            String name = pairsMatcher.group(1);
            String phoneNumber = pairsMatcher.group(2);
            phoneNumber = phoneNumber.replaceAll("[()/.\\-\\s]", "");
            result.append(String.format("<li><b>%s:</b> %s</li>", name, phoneNumber));
        }
        result.append("</ol>");

        if (isMatchFound) {
            System.out.println(result);
        } else {
            System.out.println("<p>No matches!</p>");
        }
    }
}
