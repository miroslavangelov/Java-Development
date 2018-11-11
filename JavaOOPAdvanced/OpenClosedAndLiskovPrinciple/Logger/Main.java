package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.engines.Engine;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.interfaces.Reader;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.interfaces.Writer;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.io.ConsoleInputReader;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.io.ConsoleOutputWriter;

public class Main {
    public static void main(String[] args) {
        Reader reader = new ConsoleInputReader();
        Writer writer = new ConsoleOutputWriter();

        Engine engine = new Engine(reader, writer);
        engine.run();
    }
}
