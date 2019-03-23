package JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.factories;

import JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.interfaces.Attack;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AttackFactory {
    private static final String ATTACK_PACKAGE_NAME = "JavaOOPAdvanced.OpenClosedAndLiskovPrinciple.Blobs.models.attacks.";

    public static Attack create(String attackType) {
        Attack attack = null;
        try {
            Class<?> attackClass = Class.forName(ATTACK_PACKAGE_NAME + attackType);
            Constructor<?> constructor = attackClass.getDeclaredConstructor();
            attack = (Attack) constructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return attack;
    }
}
