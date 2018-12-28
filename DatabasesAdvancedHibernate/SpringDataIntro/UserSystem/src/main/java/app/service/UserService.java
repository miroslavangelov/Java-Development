package app.service;

import app.domain.entities.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    List<String> getAllUsersByEmailProvider(String provider);

    List<User> getAllUsersLoggedInAfter(LocalDateTime date);
}
