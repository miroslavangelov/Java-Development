package app.services.contracts;

import app.domain.dtos.LoginUserDto;
import app.domain.dtos.RegisterUserDto;
import app.domain.entities.User;

public interface UserService {
    String registerUser(RegisterUserDto userDto);

    User loginUser(LoginUserDto userDto);
}
