package app.web.controllers;

import app.domain.models.binding.UserRegisterBindingModel;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController extends BaseController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerView() {
        return super.view("register");
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerAction(@ModelAttribute UserRegisterBindingModel userRegisterBindingModel) {
        if (!this.userService.registerUser(userRegisterBindingModel)) {
            return super.redirect("register");
        }

        return super.redirect("login");
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView loginView() {
        return super.view("login");
    }

    @GetMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView logout(HttpSession session){
        session.invalidate();

        return super.redirect("/");
    }
}
