package callofduty;

import callofduty.core.EngineImpl;
import callofduty.core.MissionControlImpl;
import callofduty.core.MissionManagerImpl;
import callofduty.interfaces.*;
import callofduty.io.ConsoleInputReader;
import callofduty.io.ConsoleOutputWriter;

public class Main {
    public static void main(String[] args)  {
        OutputWriter writer = new ConsoleOutputWriter();
        InputReader reader = new ConsoleInputReader();
        MissionControl missionControl = new MissionControlImpl();
        MissionManager missionManager = new MissionManagerImpl(missionControl);
        Engine engine = new EngineImpl(writer, reader, missionManager);

        engine.run();
    }
}
