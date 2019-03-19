package JavaAdvanced.StringProcessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidUsernames {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        String usernamesRegex = "(?<=[/\\\\() ]|^)([A-Za-z][A-Za-z0-9_]{2,25})(?=[/\\\\() ]|$)";
        Matcher usernamesMatcher = Pattern.compile(usernamesRegex).matcher(input);
        int sum = 0;
        ArrayList<String> usernames = new ArrayList<>();
        String[] biggestUsernames = new String[2];
        while (usernamesMatcher.find()) {
            usernames.add(usernamesMatcher.group(1));
        }
        for (int i = 0; i < usernames.size() - 1; i++) {
            if (usernames.get(i).length() + usernames.get(i + 1).length() > sum) {
                sum = usernames.get(i).length() + usernames.get(i + 1).length();
                biggestUsernames[0] =  usernames.get(i);
                biggestUsernames[1] =  usernames.get(i + 1);
            }
        }
        for (int i = 0; i < biggestUsernames.length; i++) {
            System.out.println(biggestUsernames[i]);
        }
    }
}
