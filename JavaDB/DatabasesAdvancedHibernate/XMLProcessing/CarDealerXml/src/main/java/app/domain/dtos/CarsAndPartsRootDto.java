package app.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsAndPartsRootDto {
    @XmlElement(name = "car")
    private CarDto[] carDtos;

    public CarsAndPartsRootDto() {
    }

    public CarDto[] getCarDtos() {
        return carDtos;
    }

    public void setCarDtos(CarDto[] carDtos) {
        this.carDtos = carDtos;
    }
}
