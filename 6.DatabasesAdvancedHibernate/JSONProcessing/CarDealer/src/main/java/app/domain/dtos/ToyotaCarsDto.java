package app.domain.dtos;

import com.google.gson.annotations.Expose;

public class ToyotaCarsDto {
    @Expose
    private Integer Id;

    @Expose
    private String Make;

    @Expose
    private String Model;

    @Expose
    private Long TravelledDistance;

    public ToyotaCarsDto() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        this.Make = make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        this.Model = model;
    }

    public Long getTravelledDistance() {
        return TravelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.TravelledDistance = travelledDistance;
    }
}
