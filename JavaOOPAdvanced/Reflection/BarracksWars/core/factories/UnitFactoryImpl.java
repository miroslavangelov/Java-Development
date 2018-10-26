package JavaOOPAdvanced.Reflection.BarracksWars.core.factories;

import JavaOOPAdvanced.Reflection.BarracksWars.contracts.Unit;
import JavaOOPAdvanced.Reflection.BarracksWars.contracts.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {
	private static final String UNITS_PACKAGE_NAME = "JavaOOPAdvanced.Reflection.BarracksWars.models.units.";

	@Override
	public Unit createUnit(String unitType) {
		Unit unit = null;
		try {
			Class<?> unitClass = Class.forName(UNITS_PACKAGE_NAME + unitType);
			Constructor<?> constructor = unitClass.getDeclaredConstructor();
			unit = (Unit)constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return unit;
	}
}
