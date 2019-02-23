package app.web.mbeans;

import app.domain.models.service.UserServiceModel;
import app.domain.models.view.UserFriendViewModel;
import app.service.UserService;
import app.util.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class UserFriendsBean {
    private List<UserFriendViewModel> friends;
    private UserService userService;
    private ModelMapper modelMapper;

    public UserFriendsBean(){
    }

    @Inject
    public UserFriendsBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.initFriends();
    }

    public void unfriendUser(Long id) throws IOException {
        Long userId = (Long) ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
                .getSession().getAttribute("userId");
        UserServiceModel loggedInUser = this.userService.findById(userId);

        this.userService.unfriendUser(id);
        this.userService.unfriendUser(loggedInUser.getId());

        FacesContext.getCurrentInstance().getExternalContext().redirect("/friends");
    }

    public List<UserFriendViewModel> getFriends() {
        return friends;
    }

    public void setFriends(List<UserFriendViewModel> friends) {
        this.friends = friends;
    }

    private void initFriends() {
        Long userId = (Long) ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
                .getSession().getAttribute("userId");
        UserServiceModel loggedInUser = this.userService.findById(userId);

        this.friends = loggedInUser.getFriends().stream()
                .map(friend -> this.modelMapper.map(friend, UserFriendViewModel.class))
                .collect(Collectors.toList());
    }
}
