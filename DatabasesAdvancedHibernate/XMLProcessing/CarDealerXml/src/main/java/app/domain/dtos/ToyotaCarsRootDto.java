package app.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class ToyotaCarsRootDto {
    @XmlElement(name = "car")
    private ToyotaCarsDto[] toyotaCarsDtos;

    public ToyotaCarsRootDto() {
    }

    public ToyotaCarsDto[] getToyotaCarsDtos() {
        return toyotaCarsDtos;
    }

    public void setToyotaCarsDtos(ToyotaCarsDto[] toyotaCarsDtos) {
        this.toyotaCarsDtos = toyotaCarsDtos;
    }
}
