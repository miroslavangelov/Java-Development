package JavaOOPBasics.Encapsulation.FootballTeamGenerator;

import java.util.ArrayList;

public class Team {
    private String name;
    private ArrayList<Player> players;

    public Team(String name) {
        this.setName(name);
        this.players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public double calculateTeamRating() {
        return this.players.stream().mapToDouble(Player::calculateSkill).average().orElse(0);
    }

    public void removePlayer(String playerName) {
        boolean exists = false;
        for (Player player: players) {
            if (playerName.equals(player.getName())) {
                players.remove(player);
                exists = true;
                break;
            }
        }
        if (!exists) {
            throw new IllegalArgumentException(String.format("Player %s is not in %s team.", playerName, this.getName()));
        }
    }

    private void setName(String name) {
        if (name.isEmpty() || name.trim().length() == 0 || name == null) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }
}
