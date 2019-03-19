package JavaOOPAdvanced.UnitTesting.Twitter.interfaces;

import java.util.List;

public interface Server {
    void storeMessage(String message);

    List<String> getMessages();
}
