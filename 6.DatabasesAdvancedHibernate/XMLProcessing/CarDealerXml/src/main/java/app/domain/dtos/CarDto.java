package app.domain.dtos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarDto {
    @XmlAttribute(name = "make")
    private String make;

    @XmlAttribute(name = "model")
    private String model;

    @XmlAttribute(name = "travelled-distance")
    private Long travelledDistance;

    @XmlElement(name = "parts")
    private PartsDto parts;

    public CarDto() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public PartsDto getParts() {
        return parts;
    }

    public void setParts(PartsDto parts) {
        this.parts = parts;
    }
}
