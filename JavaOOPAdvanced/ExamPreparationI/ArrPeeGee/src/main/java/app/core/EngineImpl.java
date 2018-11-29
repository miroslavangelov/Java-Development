package app.core;

import app.contracts.Battlefield;
import app.contracts.Engine;
import app.contracts.Reader;
import app.contracts.Writer;

import java.io.IOException;
import java.util.Arrays;

import static app.constants.InputCommands.*;

public class EngineImpl implements Engine {
    private Writer writer;
    private Reader reader;
    private Battlefield battlefield;

    public EngineImpl(Writer writer, Reader reader, Battlefield battlefield) {
        this.writer = writer;
        this.reader = reader;
        this.battlefield = battlefield;
    }

    @Override
    public void run() throws IOException {
        String line = this.reader.readLine();

        while (!TERMINATE_COMMAND.equals(line)){
            String[] lineTokens = line.split("\\s+");

            switch (lineTokens[0]){
                case CREATE_PARTICIPANT :
                    this.battlefield.createParticipant(lineTokens[1], lineTokens[2]);
                    break;
                case CREATE_ACTION :
                    this.battlefield.createAction(lineTokens[1],
                            Arrays.copyOf(Arrays.stream(lineTokens).skip(2).toArray(),
                                    Arrays.stream(lineTokens).skip(2).toArray().length,
                                    String[].class));
                    break;
                case STAT_PARTICIPANTS :
                    this.battlefield.reportParticipants();
                    break;
                case STAT_ACTIONS :
                    this.battlefield.reportActions();
                    break;
                case CREATE_SPECIAL :
                    this.battlefield.createSpecial(lineTokens[1], lineTokens[2]);
                    break;
            }

            line = reader.readLine();
        }
    }
}
