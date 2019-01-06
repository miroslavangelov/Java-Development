package app.services;

import app.domain.dtos.SoldProductBuyerDto;
import app.domain.dtos.UserSeedDto;
import app.domain.dtos.UserSoldProductsDto;
import app.domain.entities.Product;
import app.domain.entities.User;
import app.repositories.UserRepository;
import app.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<UserSoldProductsDto> getUsersSoldProucts() {
        List<User> users = this.userRepository.findUsersBySoldProducts();
        List<UserSoldProductsDto> userDtos = new ArrayList<>();

        for (User user: users) {
            List<SoldProductBuyerDto> productDtos = new ArrayList<>();

            user.getSoldProducts().stream()
                .filter(product -> product.getBuyer() != null)
                .forEach(product -> {
                    SoldProductBuyerDto productDto = this.modelMapper.map(product, SoldProductBuyerDto.class);
                    productDto.setBuyerFirstName(product.getBuyer().getFirstName());
                    productDto.setBuyerLastName(product.getBuyer().getLastName());
                    productDtos.add(productDto);
                });

            UserSoldProductsDto userDto = this.modelMapper.map(user, UserSoldProductsDto.class);
            userDto.setSoldProducts(productDtos);
            userDtos.add(userDto);
        }

        return userDtos;
    }
}
