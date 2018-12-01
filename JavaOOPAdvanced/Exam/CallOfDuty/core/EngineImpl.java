package callofduty.core;

import callofduty.interfaces.Engine;
import callofduty.interfaces.InputReader;
import callofduty.interfaces.MissionManager;
import callofduty.interfaces.OutputWriter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EngineImpl implements Engine {
    private OutputWriter writer;
    private InputReader reader;
    private MissionManager missionManager;

    public EngineImpl(OutputWriter writer, InputReader reader, MissionManager missionManager) {
        this.writer = writer;
        this.reader = reader;
        this.missionManager = missionManager;
    }

    @Override
    public void run() {
        String line = this.reader.readLine();

        while (true){
            List<String> lineTokens = Arrays.asList(line.split("\\s+"));
            List<String> formattedLineTokens = lineTokens.stream().skip(1).collect(Collectors.toList());

            switch (lineTokens.get(0)){
                case "Agent" :
                    this.writer.println(this.missionManager.agent(formattedLineTokens));
                    break;
                case "Request" :
                    this.writer.println(this.missionManager.request(formattedLineTokens));
                    break;
                case "Complete" :
                    this.writer.println(this.missionManager.complete(formattedLineTokens));
                    break;
                case "Status" :
                    this.writer.println(this.missionManager.status(formattedLineTokens));
                    break;
                case "Over" :
                    this.writer.println(this.missionManager.over(formattedLineTokens));
                    return;
            }

            line = reader.readLine();
        }
    }
}
