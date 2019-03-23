package mostwanted.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceSeedDto {
    @XmlElement(name = "laps")
    private Integer laps;

    @XmlElement(name = "district-name")
    private String districtName;

    @XmlElement(name = "entries")
    private EntryRootDto entryRootDto;

    public RaceSeedDto() {
    }

    public Integer getLaps() {
        return laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public EntryRootDto getEntryRootDto() {
        return entryRootDto;
    }

    public void setEntryRootDto(EntryRootDto entryRootDto) {
        this.entryRootDto = entryRootDto;
    }
}
