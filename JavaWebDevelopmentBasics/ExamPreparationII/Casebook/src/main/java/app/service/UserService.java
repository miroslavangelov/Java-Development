package app.service;

import app.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    List<UserServiceModel> findAllUsers();

    UserServiceModel userLogin(UserServiceModel userServiceModel);

    UserServiceModel findById(Long id);

    void addFriend(UserServiceModel userServiceModel);

    void unfriendUser(Long id);
}
