package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.core;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.annotations.Inject;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.*;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.Runnable;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.models.Blob;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.observers.Subject;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.utility.MutateBoolean;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Engine implements Runnable {
    private static final String TERMINATE_COMMAND = "drop";
    private static final String COMMAND_PATH = "JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.core.commands.";
    private static final String COMMAND_SUFFIX_NAME = "Command";

    private String[] data;
    private Writer writer;
    private Reader reader;
    private Repository<Blob> blobRepository;
    private Subject subject;
    private MutateBoolean mutateBoolean;

    public Engine(Reader reader, Writer writer, Repository<Blob> blobRepository, Subject subject, MutateBoolean mutateBoolean) {
        this.reader = reader;
        this.writer = writer;
        this.blobRepository = blobRepository;
        this.subject = subject;
        this.mutateBoolean = mutateBoolean;
    }

    public void run() {
        String currentLine = this.reader.readLine();

        while (!TERMINATE_COMMAND.equals(currentLine)) {
            String[] data = currentLine.split("\\s+");
            String commandName = data[0];
            interpretCommand(data, commandName);
            this.subject.notifyAllObservers();

            currentLine = this.reader.readLine();
        }
    }

    private void interpretCommand(String[] data, String commandName) {
        commandName = Arrays.stream(commandName.split("-"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(""));
        this.data = Arrays.stream(data).skip(1).toArray(String[]::new);
        Class<?> commandClass;

        try {
            commandClass = Class.forName(COMMAND_PATH + commandName + COMMAND_SUFFIX_NAME);
            Constructor<?> constructor = commandClass.getDeclaredConstructor();
            Executable command = (Executable)constructor.newInstance();
            this.injectDependencies(command);
            command.execute();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private <T> void injectDependencies(T command) throws IllegalAccessException {
        Field[] commandFields = command.getClass().getDeclaredFields();
        Field[] engineFields = this.getClass().getDeclaredFields();

        for (Field commandField: commandFields) {
            commandField.setAccessible(true);

            if (commandField.isAnnotationPresent(Inject.class)) {
                for (Field engineField: engineFields) {
                    if (commandField.getType().getSimpleName().equals(engineField.getType().getSimpleName()) && commandField.getType().equals(engineField.getType())) {
                        commandField.set(command, engineField.get(this));
                    }
                }
            }
        }
    }
}
