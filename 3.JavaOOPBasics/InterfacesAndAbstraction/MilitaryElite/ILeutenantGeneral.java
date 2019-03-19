package JavaOOPBasics.InterfacesAndAbstraction.MilitaryElite;

import java.util.List;

public interface ILeutenantGeneral extends IPrivate {
    List<Private> getPrivates();
    void addPrivate(Private privateSoldier);
}
