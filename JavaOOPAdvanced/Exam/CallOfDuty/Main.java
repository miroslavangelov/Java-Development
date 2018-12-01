package callofduty;

import callofduty.core.EngineImpl;
import callofduty.core.MissionManagerImpl;
import callofduty.interfaces.Engine;
import callofduty.interfaces.InputReader;
import callofduty.interfaces.MissionManager;
import callofduty.interfaces.OutputWriter;
import callofduty.io.ConsoleInputReader;
import callofduty.io.ConsoleOutputWriter;

public class Main {
    public static void main(String[] args)  {
        OutputWriter writer = new ConsoleOutputWriter();
        InputReader reader = new ConsoleInputReader();
        MissionManager missionManager = new MissionManagerImpl();
        Engine engine = new EngineImpl(writer, reader, missionManager);

        engine.run();
    }
}




