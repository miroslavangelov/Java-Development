package JavaOOPAdvanced.Reflection.BarracksWars.core.commands;

import JavaOOPAdvanced.Reflection.BarracksWars.annotations.Inject;
import JavaOOPAdvanced.Reflection.BarracksWars.contracts.Executable;
import JavaOOPAdvanced.Reflection.BarracksWars.contracts.Repository;
import JavaOOPAdvanced.Reflection.BarracksWars.contracts.Unit;
import JavaOOPAdvanced.Reflection.BarracksWars.contracts.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

import java.lang.reflect.InvocationTargetException;

public class AddCommand implements Executable {
    @Inject
    private String[] data;

    @Inject
    private Repository repository;

    @Inject
    private UnitFactory unitFactory;

    @Override
    public String execute() {
        String unitType = this.data[1];
        Unit unitToAdd = null;

        try {
            unitToAdd = this.unitFactory.createUnit(unitType);
        } catch (ExecutionControl.NotImplementedException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        this.repository.addUnit(unitToAdd);

        return unitType + " added!";
    }
}
