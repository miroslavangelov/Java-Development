package app.service;

import app.domain.entities.User;
import app.domain.entities.UserRole;
import app.domain.models.binding.UserEditProfileBindingModel;
import app.domain.models.binding.UserRegisterBindingModel;
import app.domain.models.service.UserServiceModel;
import app.domain.models.view.UserViewModel;
import app.repository.UserRepository;
import app.repository.UserRoleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    private UserService userService;
    private User user;
    private ModelMapper modelMapper;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.userService = new UserServiceImpl(this.userRepository, this.userRoleRepository, bCryptPasswordEncoder, this.modelMapper);

        this.user = new User();
        this.user.setUsername("Pesho");
        this.user.setPassword(bCryptPasswordEncoder.encode("pesho"));
        this.user.setEmail("pesho@abv.bg");
        this.user.setAccountNonExpired(true);
        this.user.setAccountNonLocked(true);
        this.user.setCredentialsNonExpired(true);
        this.user.setEnabled(true);
    }

    @Test
    public void registerUserWithCorrectValues() {
        long usersCount = this.userRepository.count();

        UserRegisterBindingModel userRegisterBindingModel = this.modelMapper.map(this.user, UserRegisterBindingModel.class);
        userRegisterBindingModel.setConfirmPassword(this.user.getPassword());

        this.userService.registerUser(userRegisterBindingModel);
        User actual = this.userRepository.findAll().get(0);

        Assert.assertEquals(this.user.getUsername(), actual.getUsername());
        Assert.assertEquals(this.user.getEmail(), actual.getEmail());
        Assert.assertEquals("USER", actual.getAuthorities().get(0).getAuthority());
        Assert.assertEquals("MODERATOR", actual.getAuthorities().get(1).getAuthority());
        Assert.assertEquals("ADMIN", actual.getAuthorities().get(2).getAuthority());
        Assert.assertEquals(usersCount + 1, this.userRepository.count());
    }

    @Test(expected = Exception.class)
    public void registerUserWithNullValues() {
        this.user.setUsername(null);

        this.userService.registerUser(this.modelMapper.map(this.user, UserRegisterBindingModel.class));
    }

    @Test
    public void findAllUsers() {
        this.user = this.userRepository.saveAndFlush(this.user);

        List<UserViewModel> users = this.userService.findAllUsers();

        Assert.assertEquals(this.user.getId(), users.get(0).getId());
        Assert.assertEquals(this.user.getUsername(), users.get(0).getUsername());
        Assert.assertEquals(this.user.getEmail(), users.get(0).getEmail());
        Assert.assertEquals(1, users.size());
    }

    @Test
    public void loadUserByUsername() {
        this.user = this.userRepository.saveAndFlush(this.user);

        UserDetails userDetails = this.userService.loadUserByUsername(this.user.getUsername());

        Assert.assertEquals(this.user.getUsername(), userDetails.getUsername());
    }

    @Test
    public void editUserWithCorrectValues() {
        this.user = this.userRepository.saveAndFlush(this.user);

        UserEditProfileBindingModel toBeEdited = this.modelMapper.map(this.user, UserEditProfileBindingModel.class);
        toBeEdited.setOldPassword("pesho");
        toBeEdited.setNewPassword("Secret");
        toBeEdited.setConfirmPassword("Secret");
        toBeEdited.setEmail("ivan@abv.bg");

        this.userService.editUser(toBeEdited);

        UserServiceModel actual = this.modelMapper
                .map(this.userRepository.findAll().get(0), UserServiceModel.class);

        Assert.assertEquals(actual.getUsername(), toBeEdited.getUsername());
        Assert.assertEquals(actual.getEmail(), toBeEdited.getEmail());
    }

    @Test(expected = Exception.class)
    public void editUserWithNullValues() {
        this.user = this.userRepository.saveAndFlush(this.user);

        UserEditProfileBindingModel toBeEdited = new UserEditProfileBindingModel();
        toBeEdited.setEmail(null);

        this.userService.editUser(toBeEdited);
    }

    @Test
    public void setUserRole() {
        UserRole userRole = new UserRole();
        userRole.setAuthority("USER");
        this.userRoleRepository.saveAndFlush(userRole);

        List<UserRole> roles = new ArrayList<>();
        roles.add(userRole);
        this.user.setAuthorities(roles);

        this.user = this.userRepository.saveAndFlush(this.user);

        UserRole moderator = new UserRole();
        moderator.setAuthority("MODERATOR");
        this.userRoleRepository.saveAndFlush(moderator);

        UserRole admin = new UserRole();
        admin.setAuthority("ADMIN");
        this.userRoleRepository.saveAndFlush(admin);

        this.userService.setUserRole(this.user.getId(),"moderator");
        this.userService.setUserRole(this.user.getId(),"admin");

        User actual = this.userRepository.findAll().get(0);

        Assert.assertEquals("USER", actual.getAuthorities().get(0).getAuthority());
        Assert.assertEquals("MODERATOR", actual.getAuthorities().get(1).getAuthority());
        Assert.assertEquals("ADMIN", actual.getAuthorities().get(2).getAuthority());
    }

    @Test
    public void findByUsernameWithValidData() {
        this.user = this.userRepository.saveAndFlush(this.user);

        UserServiceModel userServiceModel = this.userService.findByUsername(this.user.getUsername());

        Assert.assertEquals(userServiceModel.getUsername(), this.user.getUsername());
        Assert.assertEquals(userServiceModel.getEmail(), this.user.getEmail());
    }

    @Test(expected = Exception.class)
    public void findByUsernameWithInvalidData() {
        this.userService.findByUsername("Invalid");
    }
}
