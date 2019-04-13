package app.web.controllers;

import app.domain.models.binding.UserEditProfileBindingModel;
import app.domain.models.binding.UserRegisterBindingModel;
import app.domain.models.view.UserProfileViewModel;
import app.domain.models.view.UserViewModel;
import app.error.EmailAlreadyExistsException;
import app.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerView() {
        return super.view("register");
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerAction(@ModelAttribute UserRegisterBindingModel userRegisterBindingModel) {
        this.userService.registerUser(userRegisterBindingModel);

        return super.redirect("/users/login");
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

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView profile(ModelAndView modelAndView, Principal principal){
        UserDetails userDetails = this.userService.loadUserByUsername(principal.getName());
        UserProfileViewModel user = this.modelMapper.map(userDetails, UserProfileViewModel.class);

        modelAndView.addObject("user", user);

        return super.view("profile", modelAndView);
    }

    @GetMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editView(ModelAndView modelAndView, Principal principal){
        UserDetails userDetails = this.userService.loadUserByUsername(principal.getName());
        UserProfileViewModel user = this.modelMapper.map(userDetails, UserProfileViewModel.class);

        modelAndView.addObject("user", user);

        return super.view("edit-profile", modelAndView);
    }

    @PostMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editAction(@ModelAttribute UserEditProfileBindingModel userEditProfileBindingModel){
        this.userService.editUser(userEditProfileBindingModel);

        return super.redirect("/users/profile");
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView users(Principal principal, ModelAndView modelAndView){
        List<UserViewModel> users = this.userService.findAllUsers().stream()
                .filter(user -> !user.getUsername().equals(principal.getName()))
                .collect(Collectors.toList());
        modelAndView.addObject("users", users);

        return super.view("all-users", modelAndView);
    }

    @PostMapping("/set-moderator/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView setModerator(@PathVariable(name = "id") String id){
        this.userService.setUserRole(id, "moderator");

        return super.redirect("/users/all");
    }

    @PostMapping("/set-admin/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView setAdmin(@PathVariable(name = "id") String id){
        this.userService.setUserRole(id, "admin");

        return super.redirect("/users/all");
    }

    @ExceptionHandler({EmailAlreadyExistsException.class})
    public ModelAndView handleUserNotFound(EmailAlreadyExistsException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", e.getMessage());
        modelAndView.addObject("statusCode", e.getStatusCode());

        return modelAndView;
    }
}
