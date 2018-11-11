package JavaOOPAdvanced.UnitTesting.Twitter.models;

import JavaOOPAdvanced.UnitTesting.Twitter.interfaces.Client;
import JavaOOPAdvanced.UnitTesting.Twitter.interfaces.Server;
import JavaOOPAdvanced.UnitTesting.Twitter.interfaces.Tweet;

public class MicrowaveOven implements Client {
    private Server server;
    private String message;

    public MicrowaveOven() {
        this.server = new ServerImpl();
    }

    public String getMessage() {
        return message;
    }

    public Server getServer() {
        return server;
    }

    @Override
    public void receive(Tweet tweet) {
        this.message = tweet.retrieveMessage();
        System.out.println(this.message);
        this.server.storeMessage(this.message);
    }
}
