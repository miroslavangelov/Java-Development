package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.factories;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.interfaces.Layout;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class LayoutFactory {
    private static final String CLASS_PATH = "JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.models.layouts.";

    public static Layout create(String layoutType) {
        try {
            Class<?> layoutClass = Class.forName(CLASS_PATH + layoutType);
            Constructor<?> constructor = layoutClass.getDeclaredConstructor();
            return (Layout) constructor.newInstance();
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }
}
