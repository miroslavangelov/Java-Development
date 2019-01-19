package mostwanted.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "races")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceSeedRootDto {
    @XmlElement(name = "race")
    private RaceSeedDto[] raceSeedDtos;

    public RaceSeedRootDto() {
    }

    public RaceSeedDto[] getRaceSeedDtos() {
        return raceSeedDtos;
    }

    public void setRaceSeedDtos(RaceSeedDto[] raceSeedDtos) {
        this.raceSeedDtos = raceSeedDtos;
    }
}
