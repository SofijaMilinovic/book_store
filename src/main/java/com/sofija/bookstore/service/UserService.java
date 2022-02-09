package com.sofija.bookstore.service;

import com.sofija.bookstore.exception.UserException;
import com.sofija.bookstore.model.*;
import com.sofija.bookstore.repository.RoleRepository;
import com.sofija.bookstore.repository.UserRepository;
import com.sofija.bookstore.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private UserRoleRepository userRoleRepository;

    public List<UserModel> getAll() {
        return userRepository.findAll();
    }

    public UserModel getById(int userId) {
        return userRepository.findById(userId)
                .orElse(null);
    }

    public UserModel register(UserModel userModel) throws UserException {
        if (userRepository.findByEmail(userModel.getEmail()) != null) {
            throw new UserException("User already exists with the given email");
        }

        UserModel registeredUserModel = userRepository.save(userModel);
        addRoleToUser("ROLE_CUSTOMER", registeredUserModel.getId());
        return registeredUserModel;
    }

    public UserModel login(String email, String password) throws UserException {
        UserModel userModel = userRepository.findByEmailAndPassword(email, password);
        if (userModel == null) {
            throw new UserException("Invalid credentials");
        }
        return userModel;
    }

    public boolean isAdmin(int userId) {
        return userContainsRole(userId, "ROLE_ADMIN");
    }

    public boolean isGoldenCustomer(int userId) {
        return userContainsRole(userId, "ROLE_GOLDEN_CUSTOMER");
    }

    private boolean userContainsRole(int userId, String roleName) {
        return getById(userId)
                .getRoleModels()
                .stream()
                .anyMatch(roleModel -> roleModel.getName().equals(roleName));
    }

    public void addRoleToUser(String roleName, int userId) {
        RoleModel roleModel = roleRepository.findByName(roleName);
        UserRoleModelId userRoleModelId = new UserRoleModelId(userId, roleModel.getId());
        UserRoleModel userRoleModel = new UserRoleModel();
        userRoleModel.setUserRoleModelId(userRoleModelId);
        userRoleRepository.save(userRoleModel);
    }
}
