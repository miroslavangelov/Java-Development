package entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PlayerStatisticsId implements Serializable {
    private Game game;
    private Player player;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        PlayerStatisticsId tmpId = (PlayerStatisticsId) o;
        if(this.game.getId() != tmpId.game.getId()) return false;
        return this.player.getId() == tmpId.player.getId();
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.game, this.player);
    }
}
