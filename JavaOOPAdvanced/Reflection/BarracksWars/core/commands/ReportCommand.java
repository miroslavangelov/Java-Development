package JavaOOPAdvanced.Reflection.BarracksWars.core.commands;

import JavaOOPAdvanced.Reflection.BarracksWars.annotations.Inject;
import JavaOOPAdvanced.Reflection.BarracksWars.contracts.Executable;
import JavaOOPAdvanced.Reflection.BarracksWars.contracts.Repository;

public class ReportCommand implements Executable {
    @Inject
    private Repository repository;

    @Override
    public String execute() {
        return this.repository.getStatistics();
    }
}
