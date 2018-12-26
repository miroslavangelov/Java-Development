package entities;

import javax.persistence.*;

@Entity
@Table(name = "player_statistics")
@IdClass(PlayerStatisticsId.class)
public class PlayerStatistics {
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "game_id")
    private Game game;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "scored_goals")
    private int scoredGoals;

    @Column(name = "player_assists")
    private int playerAssists;

    @Column(name = "played_minutes")
    private int playedMinutes;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(int scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public int getPlayerAssists() {
        return playerAssists;
    }

    public void setPlayerAssists(int playerAssists) {
        this.playerAssists = playerAssists;
    }

    public int getPlayedMinutes() {
        return playedMinutes;
    }

    public void setPlayedMinutes(int playedMinutes) {
        this.playedMinutes = playedMinutes;
    }
}
