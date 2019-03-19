package app.core;

import app.contracts.*;
import app.models.participants.AbstractHero;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BattlefieldImplementation implements Battlefield {
    private Map<String, Targetable> participants;
    private List<Action> executedActions;
    private Writer writer;
    private TargetableFactory targetableFactory;
    private ActionFactory actionFactory;
    private SpecialFactory specialFactory;

    public BattlefieldImplementation(Writer writer, ActionFactory actionFactory, TargetableFactory targetableFactory, SpecialFactory specialFactory) {
        this.executedActions = new ArrayList<>();
        this.participants = new TreeMap<>();
        this.writer = writer;
        this.actionFactory = actionFactory;
        this.targetableFactory = targetableFactory;
        this.specialFactory = specialFactory;
    }

    @Override
    public void createAction(String actionName, String... participantNames) {
        try {
            Action action = this.actionFactory.create(actionName, participantNames);
            List<Targetable> actionParticipants = new ArrayList<>();

            for (String name : participantNames){
                if (this.participants.containsKey(name)){
                    actionParticipants.add(this.participants.get(name));
                } else {
                    this.writer.writeLine(String.format("%s is not on the battlefield. %s failed.", name, actionName));
                    return;
                }
            }

            this.writer.writeLine(action.executeAction(actionParticipants));
            checkForDeadParticipants();
            this.executedActions.add(action);
        } catch (Exception e) {
            this.writer.writeLine("Action does not exist.");
        }
    }

    @Override
    public void createParticipant(String name, String className) {
        if (this.participants.containsKey(name)){
            this.writer.writeLine("Participant with that name already exists.");
            return;
        }

        try {
            Targetable targetable = this.targetableFactory.create(name, className);
            this.participants.put(targetable.getName(), targetable);
            this.writer.writeLine(String.format("%s %s entered the battlefield.", targetable.getClass().getSimpleName(), targetable.getName()));
        } catch (Exception e) {
            writer.writeLine("Participant class does not exist.");
        }
    }

    @Override
    public void createSpecial(String name, String specialName) {
        try {
            AbstractHero hero = (AbstractHero) this.participants.get(name);
            Special special = this.specialFactory.create(specialName);
            hero.setSpecial(special);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void reportParticipants(){
        if (this.participants.size() < 1) {
            this.writer.writeLine("There are no participants on the battlefield.");
            return;
        }

        for (String name : this.participants.keySet()) {
            this.writer.writeLine(this.participants.get(name).toString());
            this.writer.writeLine("* * * * * * * * * * * * * * * * * * * *");
        }
    }

    @Override
    public void reportActions(){
        if (this.executedActions.size() < 1) {
            this.writer.writeLine("There are no actions on the battlefield.");
            return;
        }

        for (Action executedAction : executedActions) {
            this.writer.writeLine(executedAction.getClass().getSimpleName());
        }
    }

    private void checkForDeadParticipants(){
        Map<String, Targetable> aliveParticipants = new TreeMap<>();

        for (Targetable participant : this.participants.values()) {
            if (!participant.isAlive()){
                this.writer.writeLine(String.format("%s has been removed from the battlefield.", participant.getName()));
            } else {
                aliveParticipants.put(participant.getName(), participant);
            }
        }

        this.participants = aliveParticipants;
    }
}
