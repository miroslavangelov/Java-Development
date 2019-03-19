package app.domain.dtos;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersAndProductsDto {
    @XmlAttribute(name = "count")
    private int count;

    @XmlElement(name = "user")
    private List<UsersWithSalesDto> users;

    public UsersAndProductsDto() {
        this.users = new ArrayList<>();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<UsersWithSalesDto> getUsers() {
        return users;
    }

    public void setUsers(List<UsersWithSalesDto> users) {
        this.users = users;
    }
}
