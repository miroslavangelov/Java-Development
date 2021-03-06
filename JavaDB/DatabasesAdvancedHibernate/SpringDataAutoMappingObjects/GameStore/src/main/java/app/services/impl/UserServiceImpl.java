package app.services.impl;

import app.domain.dtos.LoginUserDto;
import app.domain.dtos.RegisterUserDto;
import app.domain.entities.User;
import app.repositories.UserRepository;
import app.services.contracts.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public String registerUser(RegisterUserDto userDto) {
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            return "The password confirmation doesn't match";
        }

        User user = modelMapper.map(userDto, User.class);
        if (this.userRepository.count() == 0){
            user.setAdmin(true);
        }

        try {
            this.userRepository.save(user);
        } catch (ConstraintViolationException cve){
            StringBuilder sb = new StringBuilder();
            cve.getConstraintViolations()
                    .forEach(s -> sb.append(s.getMessage()).append(System.lineSeparator()));
            return sb.toString();
        }
        return String.format("%s was registered", user.getFullName());
    }

    @Override
    public User loginUser(LoginUserDto userDto) {
        User user = userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
        if (user != null) {
            userRepository.save(user);
            return user;
        }

        return null;
    }
}
