package app.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSoldProductsRootDto {
    @XmlElement(name = "user")
    private List<UserSoldProductsDto> userSoldProductsDtos;

    public UserSoldProductsRootDto() {
    }

    public List<UserSoldProductsDto> getUserSoldProductsDtos() {
        return userSoldProductsDtos;
    }

    public void setUserSoldProductsDtos(List<UserSoldProductsDto> userSoldProductsDtos) {
        this.userSoldProductsDtos = userSoldProductsDtos;
    }
}
