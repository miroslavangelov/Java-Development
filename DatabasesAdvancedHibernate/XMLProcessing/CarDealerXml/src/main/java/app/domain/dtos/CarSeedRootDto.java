package app.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarSeedRootDto {
    @XmlElement(name = "car")
    private CarSeedDto[] carSeedDtos;

    public CarSeedRootDto() {
    }

    public CarSeedDto[] getCarSeedDtos() {
        return carSeedDtos;
    }

    public void setCarSeedDtos(CarSeedDto[] carSeedDtos) {
        this.carSeedDtos = carSeedDtos;
    }
}
