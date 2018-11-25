package JavaOOPAdvanced.ObjectCommunicationAndEvents.EventImplementation;

import JavaOOPAdvanced.ObjectCommunicationAndEvents.EventImplementation.models.Dispatcher;
import JavaOOPAdvanced.ObjectCommunicationAndEvents.EventImplementation.models.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        Dispatcher dispatcher = new Dispatcher();
        Handler handler = new Handler();
        dispatcher.addNameChangeListener(handler);

        while (!"End".equals(currentLine)) {
            dispatcher.setName(currentLine);
            currentLine = reader.readLine();
        }
    }
}
