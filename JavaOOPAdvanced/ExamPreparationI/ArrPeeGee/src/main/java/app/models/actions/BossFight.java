package app.models.actions;

import app.models.Config;
import app.contracts.Action;
import app.contracts.Targetable;
import app.models.participants.Boss;

import java.util.Comparator;
import java.util.List;

public class BossFight implements Action {
    @Override
    public String executeAction(List<Targetable> participants) {
        if (participants.size() < 1) {
            return "There should be at least 1 participant for boss fight!";
        }

        Targetable boss = participants.remove(0);

        if (!(boss instanceof Boss)) {
            return "Invalid boss.";
        }

        boolean aliveParticipants = true;
        Targetable killer = null;

        while (boss.isAlive() && aliveParticipants) {
            aliveParticipants = false;

            for (Targetable participant : participants) {
                participant.attack(boss);
                if (!boss.isAlive()) {
                    killer = participant;
                    participant.receiveReward(boss.getGold());
                    break;
                } else {
                    boss.attack(participant);
                    if (participant.isAlive()) {
                        aliveParticipants = true;
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();

        if (!boss.isAlive()) {
            result.append("Boss has been slain by: ");

            Targetable finalKiller = killer;
            participants.stream().filter(Targetable::isAlive)
                    .sorted(Comparator.comparing(Targetable::getName))
                    .forEach(hero -> {
                        if (!hero.getName().equals(finalKiller.getName())) {
                            hero.levelUp();
                        }
                        hero.receiveReward(Config.BOSS_INDIVIDUAL_REWARD);
                        result.append(System.lineSeparator());
                        result.append(hero.toString());
                    });
        } else {
            result.append("Boss has slain them all!");
            boss.levelUp();
        }


        return result.toString();
    }
}
