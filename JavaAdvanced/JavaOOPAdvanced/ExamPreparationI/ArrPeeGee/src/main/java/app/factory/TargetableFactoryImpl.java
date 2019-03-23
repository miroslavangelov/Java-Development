package app.factory;

import app.contracts.Targetable;
import app.contracts.TargetableFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TargetableFactoryImpl implements TargetableFactory {
    @Override
    public Targetable create(String name, String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> participantClass = Class.forName("app.models.participants." + className);
        Constructor<?> declaredConstructor = participantClass.getDeclaredConstructor();
        Targetable targetable = (Targetable) declaredConstructor.newInstance();
        targetable.setName(name);

        return targetable;
    }
}
