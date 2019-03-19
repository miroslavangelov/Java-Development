package JavaOOPAdvanced.Reflection.BarracksWars.core.commands;

import JavaOOPAdvanced.Reflection.BarracksWars.annotations.Inject;
import JavaOOPAdvanced.Reflection.BarracksWars.contracts.Executable;
import JavaOOPAdvanced.Reflection.BarracksWars.contracts.Repository;

public class RetireCommand implements Executable {
    @Inject
    private String[] data;

    @Inject
    private Repository repository;

    @Override
    public String execute() {
        String unitType = this.data[1];

        try {
            this.repository.removeUnit(unitType);
            return unitType + " retired!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}
