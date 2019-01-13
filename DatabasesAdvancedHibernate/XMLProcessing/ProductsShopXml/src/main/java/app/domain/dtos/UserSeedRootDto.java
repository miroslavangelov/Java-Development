package app.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSeedRootDto {
    @XmlElement(name = "user")
    private UserSeedDto[] userSeedDtos;

    public UserSeedRootDto() {
    }

    public UserSeedDto[] getUserSeedDtos() {
        return userSeedDtos;
    }

    public void setUserSeedDtos(UserSeedDto[] userSeedDtos) {
        this.userSeedDtos = userSeedDtos;
    }
}
