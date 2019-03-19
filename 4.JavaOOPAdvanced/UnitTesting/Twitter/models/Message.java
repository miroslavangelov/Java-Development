package JavaOOPAdvanced.UnitTesting.Twitter.models;

import JavaOOPAdvanced.UnitTesting.Twitter.interfaces.Tweet;

import java.nio.charset.Charset;
import java.util.Random;

public class Message implements Tweet {
    private final static int STRING_LENGTH = 7;

    @Override
    public String retrieveMessage() {
        byte[] array = new byte[STRING_LENGTH];
        new Random().nextBytes(array);

        return new String(array, Charset.forName("UTF-8"));
    }
}
