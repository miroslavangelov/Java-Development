package app.services;

import app.domain.dtos.UserSeedDto;
import app.domain.dtos.UserSoldProductsDto;

import java.util.List;

public interface UserService {
    void seedUsers(UserSeedDto[] userSeedDtos);

    List<UserSoldProductsDto> getUsersSoldProucts();
}
