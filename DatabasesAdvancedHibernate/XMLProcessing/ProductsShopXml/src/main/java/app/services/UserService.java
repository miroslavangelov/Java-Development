package app.services;

import app.domain.dtos.UserSeedDto;
import app.domain.dtos.UserSoldProductsRootDto;
import app.domain.dtos.UsersAndProductsDto;

public interface UserService {
    void seedUsers(UserSeedDto[] userSeedDtos);

    UserSoldProductsRootDto getUsersSoldProducts();

    UsersAndProductsDto getUsersAndProducts();
}
