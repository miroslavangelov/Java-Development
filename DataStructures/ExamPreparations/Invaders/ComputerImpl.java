package DataStructures.ExamPreparations.Invaders;

import java.util.*;

public class ComputerImpl implements Computer {
    private Set<Invader> invaders;
    private TreeMap<Integer, List<Invader>> invadersByDistance;
    private int energy;

    public ComputerImpl(int energy) {
        if (energy < 0) {
            throw new IllegalArgumentException();
        }
        this.energy = energy;
        this.invaders = new LinkedHashSet<>();
        this.invadersByDistance = new TreeMap<>();
    }

    public int getEnergy() {
        return this.energy < 0 ? 0 : this.energy;
    }

    public void addInvader(Invader invader) {
        int distance = invader.getDistance();

        this.invaders.add(invader);
        this.invadersByDistance.putIfAbsent(distance, new ArrayList<>());
        this.invadersByDistance.get(distance).add(invader);
    }

    public void skip(int turns) {
        List<Invader> invadersToRemove = new ArrayList<>();

        for (Invader invader: this.invaders) {
            int oldDistance = invader.getDistance();
            int newDistance = invader.getDistance() - turns;

            invader.setDistance(newDistance);
            this.invadersByDistance.get(oldDistance).remove(invader);
            if (newDistance <= 0) {
                this.energy -= invader.getDamage();
                invadersToRemove.add(invader);
            } else {
                this.invadersByDistance.putIfAbsent(newDistance, new ArrayList<>());
                this.invadersByDistance.get(newDistance).add(invader);
            }
        }
        for (Invader invader : invadersToRemove) {
            this.invaders.remove(invader);
        }
    }

    public void destroyTargetsInRadius(int radius) {
        List<Integer> keysToRemove = new ArrayList<>();

        this.invadersByDistance.forEach((distance, invaders) -> {
            if (distance <= radius) {
                keysToRemove.add(distance);
                for (Invader invader : invaders) {
                    this.invaders.remove(invader);
                }
            }
        });

        for (Integer distance : keysToRemove) {
            this.invadersByDistance.remove(distance);
        }
    }

    public void destroyHighestPriorityTargets(int n) {
        int removedInvaders = 0;

        for (Map.Entry<Integer, List<Invader>> pair : this.invadersByDistance.entrySet()) {
            pair.getValue().sort(Comparator.comparing(Invader::getDamage));
            for (int i = pair.getValue().size() - 1; i >= 0; i--) {
                this.invaders.remove(pair.getValue().remove(i));
                removedInvaders++;
                if (removedInvaders == n) {
                    return;
                }
            }
        }
    }

    public Iterable<Invader> invaders() {
        return this.invaders;
    }
}
