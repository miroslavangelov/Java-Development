package app.domain.dtos;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class UserSoldProductsDto {
    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private List<SoldProductBuyerDto> soldProducts;

    public UserSoldProductsDto() {
        this.soldProducts = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<SoldProductBuyerDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<SoldProductBuyerDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
