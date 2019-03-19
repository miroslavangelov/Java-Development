package app.domain.models.view;

public class UserFriendViewModel {
    private Long id;
    private String username;

    public UserFriendViewModel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
