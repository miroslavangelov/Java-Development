package app.factory;

import app.contracts.Special;
import app.contracts.SpecialFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SpecialFactoryImpl implements SpecialFactory {
    @Override
    public Special create(String specialName) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Class<?> specialClass = Class.forName("app.models.specials." + specialName);
        Constructor<?> declaredConstructor = specialClass.getDeclaredConstructor();

        return (Special) declaredConstructor.newInstance();
    }
}
