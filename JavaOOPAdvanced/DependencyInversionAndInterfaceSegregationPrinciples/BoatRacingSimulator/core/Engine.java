package JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.core;

import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.contracts.CommandHandler;
import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.contracts.Runnable;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Engine implements Runnable {
    private BufferedReader reader;
    private CommandHandler commandHandlerImpl;

    public Engine(BufferedReader reader, CommandHandler commandHandlerImpl) {
        this.reader = reader;
        this.commandHandlerImpl = commandHandlerImpl;
    }

    public void run() {
        String name;
        List<String> parameters;
        while (true) {
            try {
                String line = this.reader.readLine();

                if ("End".equals(line)) {
                    break;
                }

                List<String> tokens = Arrays.asList(line.split("\\\\"));
                name = tokens.get(0);
                parameters = tokens.stream().skip(1).collect(Collectors.toList());

                String commandResult = this.commandHandlerImpl.executeCommand(name, parameters);
                System.out.println(commandResult);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
