package app.web.mbeans;

import javax.faces.context.FacesContext;

import app.domain.models.service.UserServiceModel;
import app.domain.models.view.UserHomeViewModel;
import app.service.UserService;
import app.util.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class UserHomeBean {
    private List<UserHomeViewModel> users;
    private UserService userService;
    private ModelMapper modelMapper;

    public UserHomeBean(){
    }

    @Inject
    public UserHomeBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.initUsers();
    }

    private void initUsers() {
        Long userId = (Long) ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
                .getSession().getAttribute("userId");
        UserServiceModel loggedInUser = this.userService.findById(userId);

        this.users = this.userService.findAllUsers().stream()
                .filter(user -> !user.getUsername().equals(loggedInUser.getUsername()) &&
                        !loggedInUser.getFriends()
                                .stream()
                                .map(UserServiceModel::getUsername)
                                .collect(Collectors.toList()).contains(user.getUsername()))
                .map(user -> this.modelMapper.map(user, UserHomeViewModel.class))
                .collect(Collectors.toList());
    }

    public List<UserHomeViewModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserHomeViewModel> users) {
        this.users = users;
    }

    public void addFriend(Long id) throws IOException {
        Long userId = (Long) ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
                .getSession().getAttribute("userId");
        UserServiceModel loggedInUser = this.userService.findById(userId);
        UserServiceModel friend = this.userService.findById(id);

        loggedInUser.getFriends().add(friend);
        friend.getFriends().add(loggedInUser);

        this.userService.addFriend(loggedInUser);
        this.userService.addFriend(friend);

        FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
    }
}
