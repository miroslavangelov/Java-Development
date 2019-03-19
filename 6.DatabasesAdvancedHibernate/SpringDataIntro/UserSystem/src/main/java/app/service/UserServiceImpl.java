package app.service;

import app.domain.entities.User;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<String> getAllUsersByEmailProvider(String provider) {
        List<User> users = this.userRepository.findAllByEmailEndsWith(provider);

        return users.stream().map(
                user -> String.format("%s %s", user.getUsername(), user.getEmail())
        ).collect(Collectors.toList());
    }

    @Override
    public List<User> getAllUsersLoggedInAfter(LocalDateTime date) {
        List<User> users = this.userRepository.findAllByLastTimeLoggedInLessThan(date);
        users.forEach(user -> user.setDeleted(true));
        this.userRepository.deleteAllByDeleted(true);

        return users;
    }
}
