package app.service;

import app.domain.models.view.UserRoleViewModel;
import app.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserRoleViewModel> findAllRoles() {
         return this.userRoleRepository.findAll().stream()
                 .map(userRole -> this.modelMapper.map(userRole, UserRoleViewModel.class))
                 .collect(Collectors.toList());
    }
}
