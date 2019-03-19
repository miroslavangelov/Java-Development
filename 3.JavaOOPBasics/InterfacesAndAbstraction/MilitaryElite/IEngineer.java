package JavaOOPBasics.InterfacesAndAbstraction.MilitaryElite;

import java.util.List;

public interface IEngineer extends ISpecialisedSoldier {
    List<Repair> getRepairs();

    void addRepair(Repair repair);
}
