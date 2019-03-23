package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.factories;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.Behavior;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BehaviorFactory {
    private static final String BEHAVIOR_PACKAGE_NAME = "JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.models.behaviors.";

    public static Behavior create(String behaviorType) {
        Behavior behavior = null;
        try {
            Class<?> behaviorClass = Class.forName(BEHAVIOR_PACKAGE_NAME + behaviorType);
            Constructor<?> constructor = behaviorClass.getDeclaredConstructor();
            behavior = (Behavior) constructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return behavior;
    }
}
