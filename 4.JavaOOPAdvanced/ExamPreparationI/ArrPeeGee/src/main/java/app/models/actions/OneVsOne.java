package app.models.actions;

import app.contracts.Action;
import app.contracts.Targetable;

import java.util.List;

public class OneVsOne implements Action {
    public String executeAction(List<Targetable> participants) {
        if (participants.size() != 2) {
            return "There should be exactly 2 participants for OneVsOne!";
        }

        StringBuilder sb = new StringBuilder();

        Targetable firstHero = participants.get(0);
        Targetable secondHero = participants.get(1);

        while (firstHero.isAlive()){
            sb.append(firstHero.attack(secondHero)).append(System.lineSeparator());
            if (secondHero.isAlive()) {
                sb.append(secondHero.attack(firstHero)).append(System.lineSeparator());
            } else {
                break;
            }
        }

        Targetable winner;

        if (firstHero.isAlive()) {
            winner = firstHero;
        } else {
            winner = secondHero;
        }

        sb.append(String.format("%s is victorious!%s%s", winner.getName(), System.lineSeparator(), winner.toString()));

        return sb.toString();
    }
}
