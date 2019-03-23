package app.domain.dtos;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class CarsAndPartsDto {
    @Expose
    private CarDto car;

    @Expose
    private List<PartDto> parts;

    public CarsAndPartsDto(){
        this.parts = new ArrayList<>();
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public List<PartDto> getParts() {
        return parts;
    }

    public void setParts(List<PartDto> parts) {
        this.parts = parts;
    }
}
