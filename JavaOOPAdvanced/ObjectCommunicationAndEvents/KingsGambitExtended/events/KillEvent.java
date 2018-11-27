package JavaOOPAdvanced.ObjectCommunicationAndEvents.KingsGambitExtended.events;

import JavaOOPAdvanced.ObjectCommunicationAndEvents.KingsGambitExtended.models.Soldier;
import JavaOOPAdvanced.ObjectCommunicationAndEvents.KingsGambitExtended.models.Subject;

public class KillEvent {
    public void executeEvent(Soldier soldier, Subject subject) {
        soldier.takeHit();

        if (soldier.getHealth() <= 0) {
            subject.remove(soldier);
        }
    }
}
