package JavaOOPAdvanced.Reflection.BlackBoxInteger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        Constructor<BlackBoxInt> constructor = BlackBoxInt.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        BlackBoxInt blackBoxInt = constructor.newInstance();

        while (!"END".equals(currentLine)) {
            String[] data = currentLine.split("_");
            String methodName = data[0];
            int value = Integer.parseInt(data[1]);
            Method method = blackBoxInt.getClass().getDeclaredMethod(methodName, int.class);

            method.setAccessible(true);
            method.invoke(blackBoxInt, value);

            Field innerValue = blackBoxInt.getClass().getDeclaredField("innerValue");
            innerValue.setAccessible(true);
            System.out.println(innerValue.get(blackBoxInt));
            currentLine = reader.readLine();
        }
    }
}
