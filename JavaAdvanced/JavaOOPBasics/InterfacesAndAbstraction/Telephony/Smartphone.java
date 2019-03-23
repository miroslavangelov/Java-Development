package JavaOOPBasics.InterfacesAndAbstraction.Telephony;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Smartphone implements Calling, Browsing {
    @Override
    public String browse(String site) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(site);
        if (matcher.find()) {
            return "Invalid URL!";
        } else {
            return String.format("Browsing: %s!", site);
        }
    }

    @Override
    public String call(String number) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(number);
        if (matcher.find()) {
            return String.format("Calling... %s", number);
        } else {
            return "Invalid number!";
        }
    }
}
