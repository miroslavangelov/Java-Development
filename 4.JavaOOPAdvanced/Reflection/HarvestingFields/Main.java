package JavaOOPAdvanced.Reflection.HarvestingFields;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();

        while (!"HARVEST".equals(currentLine)) {
            printFields(currentLine);
            currentLine = reader.readLine();
        }
    }

    private static Field[] getFields(String modifier) {
        Field[] fields = RichSoilLand.class.getDeclaredFields();

        switch (modifier) {
            case "all":
                return fields;
            case "public":
                return Arrays.stream(fields)
                        .filter(field -> Modifier.isPublic(field.getModifiers()))
                        .toArray(Field[]::new);
            case "protected":
                return Arrays.stream(fields)
                        .filter(field -> Modifier.isProtected(field.getModifiers()))
                        .toArray(Field[]::new);
            case "private":
                return Arrays.stream(fields)
                        .filter(field -> Modifier.isPrivate(field.getModifiers()))
                        .toArray(Field[]::new);
        }

        return null;
    }

    private static void printFields(String modifier) {
        Field[] fields = getFields(modifier);

        for (Field field : fields) {
            System.out.println(String.format(
                    "%s %s %s",
                    Modifier.toString(field.getModifiers()),
                    field.getType().getSimpleName(),
                    field.getName())
            );
        }
    }
}
