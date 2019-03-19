package JavaOOPAdvanced.Reflection.BarracksWars.core;

import JavaOOPAdvanced.Reflection.BarracksWars.annotations.Inject;
import JavaOOPAdvanced.Reflection.BarracksWars.contracts.Executable;
import JavaOOPAdvanced.Reflection.BarracksWars.contracts.Repository;
import JavaOOPAdvanced.Reflection.BarracksWars.contracts.UnitFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Engine implements Runnable {
	private final static String COMMAND_PATH = "JavaOOPAdvanced.Reflection.BarracksWars.core.commands.";

	private String[] data;
	private Repository repository;
	private UnitFactory unitFactory;

	public Engine(Repository repository, UnitFactory unitFactory) {
		this.repository = repository;
		this.unitFactory = unitFactory;
	}

	@Override
	public void run() {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in));
		while (true) {
			try {
				String input = reader.readLine();
				String[] data = input.split("\\s+");
				String commandName = data[0];
				String result = interpredCommand(data, commandName);
				if (result.equals("fight")) {
					break;
				}
				System.out.println(result);
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String interpredCommand(String[] data, String commandName) {
		commandName = commandName.substring(0, 1).toUpperCase() + commandName.substring(1);
		this.data = data;
		Class<?> commandClass = null;
		String result = null;
		try {
			commandClass = Class.forName(COMMAND_PATH + commandName + "Command");
			Constructor<?> constructor = commandClass.getDeclaredConstructor();
			Executable command = (Executable)constructor.newInstance();
			this.injectDependencies(command);
			result = command.execute();
		} catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return result;
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
