package app.service;

import app.domain.models.view.UserRoleViewModel;

import java.util.List;

public interface UserRoleService {
    List<UserRoleViewModel> findAllRoles();
}
