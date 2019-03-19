package app.controllers;

import app.domain.dtos.LoginUserDto;
import app.domain.dtos.RegisterUserDto;
import app.domain.entities.User;
import app.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController extends BaseController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public String registerUser(String[] data) {
        String email = data[1];
        String password = data[2];
        String confirmPassword = data[3];
        String fullName = data[4];
        RegisterUserDto userDto = new RegisterUserDto(email, fullName, password, confirmPassword);

        return userService.registerUser(userDto);
    }

    public String login(String[] data) {
        String email = data[1];
        String password = data[2];
        LoginUserDto loginUserDto = new LoginUserDto(email, password);
        User user =  userService.loginUser(loginUserDto);

        if (user == null) {
            return "Incorrect username / password";
        }
        userSession.login(user);

        return String.format("Successfully logged in %s", user.getFullName());
    }

    public String logout() {
        User currentUser = userSession.getCurrentUser();
        if (currentUser == null) {
            return "Cannot log out. No user was logged in.";
        }
        userSession.logout();
        return String.format("User %s successfully logged out", currentUser.getFullName());
    }
}
