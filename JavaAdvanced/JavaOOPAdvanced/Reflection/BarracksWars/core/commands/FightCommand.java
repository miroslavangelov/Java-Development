package JavaOOPAdvanced.Reflection.BarracksWars.core.commands;

import JavaOOPAdvanced.Reflection.BarracksWars.contracts.Executable;

public class FightCommand implements Executable {
    @Override
    public String execute() {
        return "fight";
    }
}
