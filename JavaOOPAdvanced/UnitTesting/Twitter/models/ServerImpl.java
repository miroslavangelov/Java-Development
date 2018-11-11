package JavaOOPAdvanced.UnitTesting.Twitter.models;

import JavaOOPAdvanced.UnitTesting.Twitter.interfaces.Server;

import java.util.ArrayList;
import java.util.List;

public class ServerImpl implements Server {
    private List<String> messages;

    public ServerImpl() {
        this.messages = new ArrayList<>();
    }

    public List<String> getMessages() {
        return messages;
    }

    @Override
    public void storeMessage(String message) {
        this.messages.add(message);
    }
}
