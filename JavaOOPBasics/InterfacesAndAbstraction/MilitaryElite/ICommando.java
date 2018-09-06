package JavaOOPBasics.InterfacesAndAbstraction.MilitaryElite;

import java.util.List;

public interface ICommando extends ISpecialisedSoldier {
    List<Mission> getMissions();

    void addMission(Mission mission);
}
