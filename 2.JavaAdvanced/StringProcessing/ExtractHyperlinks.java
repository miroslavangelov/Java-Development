package JavaAdvanced.StringProcessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractHyperlinks {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String hyperlinksRegex = "<a\\s+([^>]+)?href\\s*=\\s*('([^']*)'|\"([^\"]*)\"|([^\\s>]*))[^>]*>";

        StringBuilder text = new StringBuilder();
        String line = reader.readLine();

        while (!line.equals("END")) {
            text.append(line);
            line = reader.readLine();
        }

        Matcher hyperlinksMatcher = Pattern.compile(hyperlinksRegex).matcher(text);

        while (hyperlinksMatcher.find()) {
            for (int i = 3; i <= 5; i++) {
                String group = hyperlinksMatcher.group(i);

                if (group != null) {
                    System.out.println(group);
                }
            }
        }
    }
}
