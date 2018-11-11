package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.io;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.interfaces.Writer;

public class ConsoleOutputWriter implements Writer {
    @Override
    public void write(String line) {
        System.out.print(line);
    }

    @Override
    public void writeLine(String line) {
        System.out.println(line);
    }
}
