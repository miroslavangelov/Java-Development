package app.service;

import app.domain.entities.User;
import app.domain.entities.UserRole;
import app.domain.models.binding.UserEditBindingModel;
import app.domain.models.binding.UserRegisterBindingModel;
import app.domain.models.view.UserViewModel;
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
    public boolean registerUser(UserRegisterBindingModel userRegisterBindingModel) {
        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            return false;
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

        try {
            this.userRepository.saveAndFlush(user);

            return true;
        } catch (Exception e){
            e.printStackTrace();

            return false;
        }
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
    public UserEditBindingModel findUserById(String id) {
        User user = this.userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new IllegalArgumentException("User does not exist.");
        }

        UserEditBindingModel userEditBindingModel = this.modelMapper.map(user, UserEditBindingModel.class);
        for (UserRole userRole: user.getAuthorities()) {
            userEditBindingModel.getUserRoles().add(userRole.getAuthority());
        }

        return userEditBindingModel;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    @Override
    public void editUser(UserEditBindingModel userEditBindingModel) {
        User user = this.userRepository.findByUsername(userEditBindingModel.getUsername()).orElse(null);

        if (user == null) {
            throw new IllegalArgumentException("User does not exist.");
        }

        List<UserRole> roles = new ArrayList<>();

        for (String authority : userEditBindingModel.getUserRoles()) {
            UserRole role = this.userRoleRepository.findByAuthority(authority);

            roles.add(role);
        }

        user.setAuthorities(roles);
        this.userRepository.saveAndFlush(user);
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
