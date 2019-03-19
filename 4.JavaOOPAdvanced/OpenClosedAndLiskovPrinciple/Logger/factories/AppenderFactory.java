package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.factories;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.interfaces.Appender;
import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.interfaces.Layout;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AppenderFactory {
    private static final String CLASS_PATH = "JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Logger.models.appenders.";

    public static Appender create(String appenderType, Layout layout) {
        try {
            Class<?> layoutClass = Class.forName(CLASS_PATH + appenderType);
            Constructor constructor = layoutClass.getDeclaredConstructor(Layout.class);
            return (Appender) constructor.newInstance(layout);
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }
}
