package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.io;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.Writer;

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
