package app.services;

import app.domain.dtos.*;
import app.domain.entities.User;
import app.repositories.UserRepository;
import app.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedUsers(UserSeedDto[] userSeedDtos) {
        for (UserSeedDto userDto: userSeedDtos) {
            if (!this.validatorUtil.isValid(userDto)) {
                this.validatorUtil.violations(userDto)
                        .forEach(violation -> System.out.println(violation.getMessage()));

                continue;
            }

            User user = this.modelMapper.map(userDto, User.class);
            this.userRepository.saveAndFlush(user);
        }
    }

    @Override
    public List<UserSoldProductsDto> getUsersSoldProducts() {
        List<User> users = this.userRepository.findUsersBySoldProducts();
        List<UserSoldProductsDto> userDtos = new ArrayList<>();

        for (User user: users) {
            List<SoldProductBuyerDto> productDtos = new ArrayList<>();

            user.getSoldProducts().stream()
                .filter(product -> product.getBuyer() != null)
                .forEach(product -> {
                    SoldProductBuyerDto productDto = this.modelMapper.map(product, SoldProductBuyerDto.class);
                    productDtos.add(productDto);
                });

            UserSoldProductsDto userDto = this.modelMapper.map(user, UserSoldProductsDto.class);
            userDto.setSoldProducts(productDtos);
            userDtos.add(userDto);
        }

        return userDtos;
    }

    @Override
    public UsersAndProductsDto getUsersAndProducts() {
        List<User> usersWithSales = this.userRepository.findUsersBySoldProducts();
        UsersAndProductsDto userDto = new UsersAndProductsDto();
        List<UsersWithSalesDto> usersWithSalesDtos = new ArrayList<>();

        for (User user: usersWithSales) {
            List<ProductsDto> productsDtos = new ArrayList<>();

            user.getSoldProducts().stream()
                    .filter(product -> product.getBuyer() != null)
                    .forEach(product -> {
                        ProductsDto productsDto = this.modelMapper.map(product, ProductsDto.class);
                        productsDtos.add(productsDto);
                    });
            SoldProductsDto soldProductsDto = new SoldProductsDto();
            soldProductsDto.setProducts(productsDtos);
            soldProductsDto.setCount(productsDtos.size());

            UsersWithSalesDto usersWithSalesDto = this.modelMapper.map(user, UsersWithSalesDto.class);
            usersWithSalesDto.setSoldProducts(soldProductsDto);
            usersWithSalesDtos.add(usersWithSalesDto);
        }

        usersWithSalesDtos = usersWithSalesDtos.stream()
                .sorted((firstUser, secondUser) -> {
                    int cmp = secondUser.getSoldProducts().getCount() - firstUser.getSoldProducts().getCount();
                    if (cmp == 0) {
                        cmp = firstUser.getLastName().compareTo(secondUser.getLastName());
                    }
                    return cmp;
                })
                .collect(Collectors.toList());

        userDto.setUsersCount(usersWithSalesDtos.size());
        userDto.setUsers(usersWithSalesDtos);

        return userDto;
    }
}
