package app.web.mbeans;

import app.domain.models.service.UserServiceModel;
import app.domain.models.view.UserProfileViewModel;
import app.service.UserService;
import app.util.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class UserProfileBean {
    private UserProfileViewModel userProfileViewModel;
    private UserService userService;
    private ModelMapper modelMapper;

    public UserProfileBean() {

    }

    @Inject
    public UserProfileBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.initUser();
    }

    private void initUser() {
        String userIdParam = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()).get("id");
        Long userId;

        if (userIdParam == null) {
            userId = (Long) ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest())
                    .getSession().getAttribute("userId");
        } else {
            userId = Long.parseLong(userIdParam);
        }

        UserServiceModel user = this.userService.findById(userId);
        this.userProfileViewModel = this.modelMapper.map(user, UserProfileViewModel.class);
    }

    public UserProfileViewModel getUserProfileBean() {
        return userProfileViewModel;
    }

    public void setUserProfileBean(UserProfileViewModel userProfileViewModel) {
        this.userProfileViewModel = userProfileViewModel;
    }
}
