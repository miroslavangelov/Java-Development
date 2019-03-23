package app.service;

import app.domain.models.binding.UserEditBindingModel;
import app.domain.models.binding.UserRegisterBindingModel;
import app.domain.models.view.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean registerUser(UserRegisterBindingModel userRegisterBindingModel);

    List<UserViewModel> findAllUsers();

    UserEditBindingModel findUserById(String id);

    void editUser(UserEditBindingModel userEditBindingModel);
}
