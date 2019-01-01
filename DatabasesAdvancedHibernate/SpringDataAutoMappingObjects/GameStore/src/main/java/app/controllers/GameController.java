package app.controllers;

import app.domain.dtos.LoginUserDto;
import app.domain.dtos.RegisterUserDto;
import app.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Controller
public class GameController implements CommandLineRunner {
    private UserService userService;

    @Autowired
    public GameController(UserService userService) {
        this.userService = userService;
    }
    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        LoginUserDto loginUserDto = null;

        while (true) {
            String[] data = reader.readLine().split("\\|");
            String command = data[0];

            switch (command) {
                case "RegisterUser":
                    String email = data[1];
                    String password = data[2];
                    String confirmPassword = data[3];
                    String fullName = data[4];
                    RegisterUserDto userDto = new RegisterUserDto(email, fullName, password, confirmPassword);
                    System.out.println(userService.registerUser(userDto));
                    break;
                case "LoginUser":
                    email = data[1];
                    password = data[2];
                    loginUserDto = new LoginUserDto(email, password);
                    System.out.println(userService.loginUser(loginUserDto));
                    break;
                case "Logout":
                    System.out.println(userService.logoutUser(loginUserDto));
                    break;
            }
        }
    }
}
