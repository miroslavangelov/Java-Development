package app.domain.dtos;

import app.domain.entities.Sale;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderedCustomersDto {
    @XmlElement(name = "id")
    private Integer id;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "birth-date")
    private Date birthDate;

    @XmlElement(name = "is-young-driver")
    private Boolean isYoungDriver;

    private List<Sale> Sales;

    public OrderedCustomersDto() {
        this.Sales = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
