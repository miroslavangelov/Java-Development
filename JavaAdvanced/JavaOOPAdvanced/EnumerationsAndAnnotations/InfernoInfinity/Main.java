package JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity;

import JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity.engines.Engine;
import JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity.io.ConsoleInputReader;
import JavaOOPAdvanced.EnumerationsAndAnnotations.InfernoInfinity.io.ConsoleOutputWriter;

public class Main {
    public static void main(String[] args) {
        ConsoleInputReader inputReader = new ConsoleInputReader();
        ConsoleOutputWriter outputWriter = new ConsoleOutputWriter();
        Engine engine = new Engine(inputReader, outputWriter);

        engine.run();
    }
}
