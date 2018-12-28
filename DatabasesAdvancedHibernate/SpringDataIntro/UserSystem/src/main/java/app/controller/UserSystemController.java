package app.controller;

import app.domain.entities.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
public class UserSystemController implements CommandLineRunner {
    private final UserService userService;

    @Autowired
    public UserSystemController(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

//        String emailProvider = reader.readLine();
//        List<String> usersByEmailProvider = this.userService.getAllUsersByEmailProvider(emailProvider);
//        if (usersByEmailProvider.size() > 0) {
//            usersByEmailProvider.forEach(System.out::println);
//        } else {
//            System.out.println(String.format("No users found with email domain %s", emailProvider));
//        }

        String inputDate = reader.readLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate date = LocalDate.parse(inputDate, formatter);
        LocalDateTime dateTime = LocalDateTime.of(date, LocalDateTime.now().toLocalTime());
        List<User> usersLoggedInAfter = this.userService.getAllUsersLoggedInAfter(dateTime);
        if (usersLoggedInAfter.size() == 0) {
            System.out.println("No users have been deleted");
        } else {
            usersLoggedInAfter.forEach(user -> System.out.println(String.format(
                    "%d %s been deleted",
                    usersLoggedInAfter.size(),
                    usersLoggedInAfter.size() > 1 ? "users have" : "user have"
            )));
        }
    }
}
