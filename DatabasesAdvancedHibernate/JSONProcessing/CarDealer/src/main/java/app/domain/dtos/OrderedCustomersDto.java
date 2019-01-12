package app.domain.dtos;

import app.domain.entities.Sale;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderedCustomersDto {
    @Expose
    private Integer Id;

    @Expose
    private String Name;

    @Expose
    private Date BirthDate;

    @Expose
    private Boolean IsYoungDriver;

    @Expose
    private List<Sale> Sales;

    public OrderedCustomersDto() {
        this.Sales = new ArrayList<>();
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public Boolean getYoungDriver() {
        return IsYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        IsYoungDriver = youngDriver;
    }
}
