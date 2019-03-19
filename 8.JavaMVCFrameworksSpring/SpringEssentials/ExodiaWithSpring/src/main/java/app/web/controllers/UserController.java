package app.web.controllers;

import app.domain.models.binding.UserLoginBindingModel;
import app.domain.models.binding.UserRegisterBindingModel;
import app.domain.models.service.UserServiceModel;
import app.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public ModelAndView loginView(ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") != null) {
            modelAndView.setViewName("redirect:/home");

            return modelAndView;
        }

        modelAndView.setViewName("login");

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView loginAction(@ModelAttribute UserLoginBindingModel userLoginBindingModel, ModelAndView modelAndView, HttpSession httpSession) {
        UserServiceModel userServiceModel = this.userService.loginUser(this.modelMapper.map(userLoginBindingModel, UserServiceModel.class));

        if (userServiceModel == null) {
            throw new IllegalArgumentException("Wrong username or password!");
        }

        httpSession.setAttribute("username", userServiceModel.getUsername());
        httpSession.setAttribute("userId", userServiceModel.getId());

        modelAndView.setViewName("redirect:/home");

        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView registerView(ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") != null) {
            modelAndView.setViewName("redirect:/home");

            return modelAndView;
        }

        modelAndView.setViewName("register");

        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerAction(@ModelAttribute UserRegisterBindingModel userRegisterBindingModel, ModelAndView modelAndView) {
        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords don't match!");
        }

        this.userService.registerUser(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        modelAndView.setViewName("redirect:/login");

        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpSession httpSession) {
        httpSession.invalidate();
        modelAndView.setViewName("redirect:/");

        return modelAndView;
    }
}
