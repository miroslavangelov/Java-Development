package JavaAdvanced.DataRepresentationAndManipulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SemanticHTML {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nonSemanticDivsRegex = "(?<padding>\\s*)?<div\\s+.*(?<groupForRemove>(id|class)\\s*?=\\s*?\"(?<tag>[a-z]+)\").*>";
        String closingTagsRegex = "<\\/div\\s*>(?<groupForRemove>\\s*<!--\\s*(?<tag>[a-z]+)\\s*-->)";
        ArrayList<String> html = new ArrayList<>();
        String line = reader.readLine();

        while(!line.equals("END")) {
            html.add(line);
            line = reader.readLine();
        }

        for (int i = 0; i < html.size(); i++) {
            Matcher nonSemanticDivsMatcher = Pattern.compile(nonSemanticDivsRegex).matcher(html.get(i));
            Matcher closingTagsMatcher = Pattern.compile(closingTagsRegex).matcher(html.get(i));
            if (nonSemanticDivsMatcher.find()) {
                String changedLine = html.get(i)
                        .replace("<div", "<" + nonSemanticDivsMatcher.group("tag"))
                        .replace(nonSemanticDivsMatcher.group("groupForRemove"), "")
                        .replaceAll(" +", " ")
                        .replaceAll(" +>", ">")
                        .replaceAll(" <", nonSemanticDivsMatcher.group("padding") + "<");
                html.set(i, changedLine);
            }
            if (closingTagsMatcher.find()) {
                String changedLine = html.get(i)
                        .replace("</div>", "</" + closingTagsMatcher.group("tag") + ">")
                        .replace(closingTagsMatcher.group("groupForRemove"), "");
                html.set(i, changedLine);
            }
            System.out.println(html.get(i));
        }
    }
}
