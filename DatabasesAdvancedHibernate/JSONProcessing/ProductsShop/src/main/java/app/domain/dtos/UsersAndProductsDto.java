package app.domain.dtos;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class UsersAndProductsDto {
    @Expose
    private int usersCount;

    @Expose
    private List<UsersWithSalesDto> users;

    public UsersAndProductsDto() {
        this.users = new ArrayList<>();
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public List<UsersWithSalesDto> getUsers() {
        return users;
    }

    public void setUsers(List<UsersWithSalesDto> users) {
        this.users = users;
    }
}
