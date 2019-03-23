package app.domain.models.binding;

import java.util.ArrayList;
import java.util.List;

public class UserEditBindingModel {
    private String id;
    private String username;
    private String email;
    private List<String> userRoles;

    public UserEditBindingModel() {
        this.userRoles = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }
}
