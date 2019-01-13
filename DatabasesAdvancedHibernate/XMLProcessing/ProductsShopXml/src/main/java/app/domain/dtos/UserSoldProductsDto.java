package app.domain.dtos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSoldProductsDto {
    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElement(name = "sold-products")
    private SoldProductBuyerRootDto soldProducts;

    public UserSoldProductsDto() {
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

    public SoldProductBuyerRootDto getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(SoldProductBuyerRootDto soldProducts) {
        this.soldProducts = soldProducts;
    }
}
