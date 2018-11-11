package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.io;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.interfaces.Reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputReader implements Reader {
    private BufferedReader reader;

    public ConsoleInputReader() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String readLine() {
        String line = null;

        try {
            line = this.reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }
}
