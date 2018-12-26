package entities;

import javax.persistence.*;

@Entity
@Table(name = "positions")
public class Position {
    private String id;
    private String positionDescription;

    @Id
    @Column(name = "id", length = 2, unique = true)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "position_description")
    public String getPositionDescription() {
        return positionDescription;
    }

    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }
}
