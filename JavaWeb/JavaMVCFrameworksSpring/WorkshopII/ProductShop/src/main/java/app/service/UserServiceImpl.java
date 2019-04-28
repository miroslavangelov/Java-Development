package app.service;

import app.domain.entities.User;
import app.domain.entities.UserRole;
import app.domain.models.binding.UserEditProfileBindingModel;
import app.domain.models.binding.UserRegisterBindingModel;
import app.domain.models.service.UserServiceModel;
import app.domain.models.view.UserViewModel;
import app.error.EmailAlreadyExistsException;
import app.repository.UserRepository;
import app.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegisterBindingModel userRegisterBindingModel) {
        User existingUser = this.userRepository.findByUsername(userRegisterBindingModel.getUsername()).orElse(null);

        if (existingUser != null) {
            throw new IllegalArgumentException("User already exists!");
        }

        User userByEmail = this.userRepository.findByEmail(userRegisterBindingModel.getEmail()).orElse(null);
        if (userByEmail != null) {
            throw new EmailAlreadyExistsException("Email already exists!");
        }

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords don't match!");
        }

        User user = this.modelMapper.map(userRegisterBindingModel, User.class);
        user.setPassword(this.bCryptPasswordEncoder.encode(userRegisterBindingModel.getPassword()));
        this.insertUserRoles();

        if (this.userRepository.count() == 0) {
            user.getAuthorities().add(this.userRoleRepository.findByAuthority("USER"));
            user.getAuthorities().add(this.userRoleRepository.findByAuthority("MODERATOR"));
            user.getAuthorities().add(this.userRoleRepository.findByAuthority("ADMIN"));
        } else {
            user.getAuthorities().add(this.userRoleRepository.findByAuthority("USER"));
        }

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public List<UserViewModel> findAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserViewModel> userViewModels = new ArrayList<>();

        for (User user : users) {
            UserViewModel userViewModel = this.modelMapper.map(user, UserViewModel.class);
            userViewModel.setRoles(user.getAuthorities().stream()
                    .map(UserRole::getAuthority).collect(Collectors.joining(", ")));
            userViewModels.add(userViewModel);
        }

        return userViewModels;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    @Override
    public void editUser(UserEditProfileBindingModel userEditProfileBindingModel) {
        User user = this.userRepository.findByUsername(userEditProfileBindingModel.getUsername()).orElse(null);

        if (user == null) {
            throw new IllegalArgumentException("User does not exist.");
        }

        if (!this.bCryptPasswordEncoder.matches(userEditProfileBindingModel.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Wrong password.");
        }

        if (!userEditProfileBindingModel.getNewPassword().equals(userEditProfileBindingModel.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords don't match.");
        }

        User userByEmail = this.userRepository.findByEmail(userEditProfileBindingModel.getEmail()).orElse(null);
        if (userByEmail != null && !userByEmail.getEmail().equals(user.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists!");
        }

        user.setPassword(this.bCryptPasswordEncoder.encode(userEditProfileBindingModel.getNewPassword()));
        user.setEmail(userEditProfileBindingModel.getEmail());

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void setUserRole(String id, String role) {
        User user = this.userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new IllegalArgumentException("User does not exist.");
        }

        switch (role) {
            case "moderator":
                user.getAuthorities().add(this.userRoleRepository.findByAuthority("MODERATOR"));
                break;
            case "admin":
                UserRole moderator = this.userRoleRepository.findByAuthority("MODERATOR");

                if (!user.getAuthorities().contains(moderator)) {
                    user.getAuthorities().add(moderator);
                }

                user.getAuthorities().add(this.userRoleRepository.findByAuthority("ADMIN"));
                break;
        }

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        User user = this.userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            throw new IllegalArgumentException("User does not exist.");
        }

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    private void insertUserRoles() {
        if (this.userRoleRepository.count() == 0) {
            UserRole user = new UserRole();
            user.setAuthority("USER");
            this.userRoleRepository.save(user);

            UserRole moderator = new UserRole();
            moderator.setAuthority("MODERATOR");
            this.userRoleRepository.save(moderator);

            UserRole admin = new UserRole();
            admin.setAuthority("ADMIN");
            this.userRoleRepository.save(admin);
        }
    }
}
