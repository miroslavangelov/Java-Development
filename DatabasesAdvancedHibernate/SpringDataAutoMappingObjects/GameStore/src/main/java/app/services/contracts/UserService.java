package app.services.contracts;

import app.domain.dtos.LoginUserDto;
import app.domain.dtos.RegisterUserDto;

public interface UserService {
    String registerUser(RegisterUserDto userDto);

    String loginUser(LoginUserDto userDto);

    String logoutUser(LoginUserDto userDto);
}
