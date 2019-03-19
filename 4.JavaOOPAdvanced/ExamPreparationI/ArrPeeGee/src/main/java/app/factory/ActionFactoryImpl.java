package app.factory;

import app.contracts.Action;
import app.contracts.ActionFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class ActionFactoryImpl implements ActionFactory {
    @Override
    public Action create(String actionName, String... participantNames) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> actionClass = Class.forName("app.models.actions." + actionName);
        Constructor<?> declaredConstructor = actionClass.getDeclaredConstructor();

        return (Action) declaredConstructor.newInstance();
    }
}
