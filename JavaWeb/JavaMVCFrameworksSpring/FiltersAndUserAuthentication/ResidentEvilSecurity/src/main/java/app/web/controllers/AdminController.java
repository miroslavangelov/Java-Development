package app.web.controllers;

import app.domain.models.binding.UserEditBindingModel;
import app.domain.models.view.UserRoleViewModel;
import app.domain.models.view.UserViewModel;
import app.service.UserRoleService;
import app.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminController extends BaseController {
    private final UserService userService;
    private final UserRoleService userRoleService;

    @Autowired
    public AdminController(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView users(Principal principal, ModelAndView modelAndView) {
        List<UserViewModel> users = this.userService.findAllUsers().stream()
                .filter(user -> !user.getUsername().equals(principal.getName()))
                .collect(Collectors.toList());
        modelAndView.addObject("users", users);

        return super.view("users", modelAndView);
    }

    @GetMapping("users/edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView editView(@PathVariable(name = "id") String id, ModelAndView modelAndView, Principal principal) {
        UserEditBindingModel userEditBindingModel = this.userService.findUserById(id);

        if (userEditBindingModel.getUsername().equals(principal.getName())) {
            return super.redirect("/unauthorized");
        }

        modelAndView.addObject("user", userEditBindingModel);
        List<UserRoleViewModel> userRoles = this.userRoleService.findAllRoles();
        modelAndView.addObject("roles", userRoles);

        return super.view("edit-user", modelAndView);
    }

    @PostMapping("users/edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView editAction(@PathVariable(name = "id") String id, UserEditBindingModel userEditBindingModel) {
        UserEditBindingModel user = this.userService.findUserById(id);

        if (user.getUserRoles().contains("ADMIN")) {
            return super.redirect("/unauthorized");
        }

        this.userService.editUser(userEditBindingModel);

        return super.redirect("/users");
    }
}
