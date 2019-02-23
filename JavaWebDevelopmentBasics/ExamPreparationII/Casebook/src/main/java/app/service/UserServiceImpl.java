package app.service;

import app.domain.entities.User;
import app.domain.models.service.UserServiceModel;
import app.repository.UserRepository;
import app.util.ModelMapper;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        this.userRepository.save(user);
    }

    @Override
    public List<UserServiceModel> findAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserServiceModel> userServiceModels = new ArrayList<>();

        users.forEach(cat -> {
            UserServiceModel userServiceModel = this.modelMapper.map(cat, UserServiceModel.class);
            userServiceModels.add(userServiceModel);
        });

        return userServiceModels;
    }

    @Override
    public UserServiceModel userLogin(UserServiceModel userServiceModel) {
        User user = this.userRepository.findByUserBane(userServiceModel.getUsername());

        if (user == null || !DigestUtils.sha256Hex(userServiceModel.getPassword()).equals(user.getPassword())) {
            return null;
        }

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findById(Long id) {
        User user = this.userRepository.findById(id);

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public void addFriend(UserServiceModel userServiceModel) {
        this.userRepository.update(this.modelMapper.map(userServiceModel, User.class));
    }

    @Override
    public void unfriendUser(Long id) {
        this.userRepository.unfriendUser(id);
    }
}
