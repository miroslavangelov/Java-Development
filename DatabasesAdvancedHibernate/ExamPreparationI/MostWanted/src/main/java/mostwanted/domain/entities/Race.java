package mostwanted.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "races")
public class Race extends BaseEntity {
    private Integer laps = 0;
    private District district;
    private List<RaceEntry> entries;

    public Race() {
    }

    @NotNull
    @Column(name = "laps")
    public Integer getLaps() {
        return laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    @ManyToOne(targetEntity = District.class)
    @JoinColumn(name = "district_id")
    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @OneToMany(mappedBy = "race", fetch = FetchType.EAGER)
    public List<RaceEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<RaceEntry> entries) {
        this.entries = entries;
    }
}
