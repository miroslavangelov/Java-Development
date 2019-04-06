package app.service;

import app.domain.models.binding.UserEditProfileBindingModel;
import app.domain.models.binding.UserRegisterBindingModel;
import app.domain.models.service.UserServiceModel;
import app.domain.models.view.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean registerUser(UserRegisterBindingModel userRegisterBindingModel);

    void editUser(UserEditProfileBindingModel userEditProfileBindingModel);

    List<UserViewModel> findAllUsers();

    void setUserRole(String id, String role);

    UserServiceModel findByUsername(String username);
}
