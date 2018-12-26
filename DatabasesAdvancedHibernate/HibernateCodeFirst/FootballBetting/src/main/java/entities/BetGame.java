package entities;

import javax.persistence.*;

@Entity
@Table(name = "bet_games")
@IdClass(BetGameId.class)
public class BetGame {
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "game_id")
    private Game game;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "bet_id")
    private Bet bet;

    @OneToOne(optional = false)
    @JoinColumn(name = "result_prediction")
    private ResultPrediction resultPrediction;

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Bet getBet() {
        return this.bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public ResultPrediction getResultPrediction() {
        return this.resultPrediction;
    }

    public void setResultPrediction(ResultPrediction resultPrediction) {
        this.resultPrediction = resultPrediction;
    }
}