package entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BetGameId implements Serializable {
    private Bet bet;
    private Game game;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        BetGameId tmpId = (BetGameId) o;
        if(this.bet.getId() != tmpId.bet.getId()) return false;
        return this.game.getId() == tmpId.game.getId();
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.bet, this.game);
    }
}
