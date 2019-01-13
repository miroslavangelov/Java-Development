package app.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartSeedRootDto {
    @XmlElement(name = "part")
    private PartSeedDto[] partSeedDtos;

    public PartSeedRootDto() {
    }

    public PartSeedDto[] getPartSeedDtos() {
        return partSeedDtos;
    }

    public void setPartSeedDtos(PartSeedDto[] partSeedDtos) {
        this.partSeedDtos = partSeedDtos;
    }
}
