package app.web.mbeans;

import app.domain.models.binding.UserRegisterBindingModel;
import app.domain.models.service.UserServiceModel;
import app.service.UserService;
import app.util.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class UserRegisterBean {
    private UserRegisterBindingModel userRegisterBindingModel;
    private UserService userService;
    private ModelMapper modelMapper;

    public UserRegisterBean() {
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper) {
        this();
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }

    public void register() throws IOException {
        if (!this.userRegisterBindingModel.getPassword().equals(this.userRegisterBindingModel.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords don't match!");
        }

        this.userService.registerUser(this.modelMapper.map(this.userRegisterBindingModel, UserServiceModel.class));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/login");
    }
}
