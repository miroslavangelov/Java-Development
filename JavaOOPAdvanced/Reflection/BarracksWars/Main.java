package JavaOOPAdvanced.Reflection.BarracksWars;


import JavaOOPAdvanced.Reflection.BarracksWars.contracts.Repository;
import JavaOOPAdvanced.Reflection.BarracksWars.contracts.UnitFactory;
import JavaOOPAdvanced.Reflection.BarracksWars.core.Engine;
import JavaOOPAdvanced.Reflection.BarracksWars.core.factories.UnitFactoryImpl;
import JavaOOPAdvanced.Reflection.BarracksWars.data.UnitRepository;

public class Main {

	public static void main(String[] args) {
		Repository repository = new UnitRepository();
		UnitFactory unitFactory = new UnitFactoryImpl();
		Runnable engine = new Engine(repository, unitFactory);
		engine.run();
	}
}
