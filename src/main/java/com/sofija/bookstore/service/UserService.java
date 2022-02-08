package com.sofija.bookstore.service;

import com.sofija.bookstore.exception.UserException;
import com.sofija.bookstore.model.RoleModel;
import com.sofija.bookstore.model.UserModel;
import com.sofija.bookstore.model.UserRoleModel;
import com.sofija.bookstore.model.UserRoleModelId;
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
        if (!userRepository.findByEmail(userModel.getEmail()).isEmpty()) {
            throw new UserException("User already exists with the given email");
        }

        UserModel registeredUserModel = userRepository.save(userModel);
        addCustomerRoleToUser(registeredUserModel);
        return registeredUserModel;
    }

    public UserModel login(UserModel userModel) throws UserException {
        List<UserModel> userModels = userRepository.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword());
        if (userModels.isEmpty()) {
            throw new UserException("Invalid credentials");
        }
        return userModels.get(0);
    }

    private void addCustomerRoleToUser(UserModel registeredUserModel) {
        RoleModel customerRoleModel = roleRepository.findByName("ROLE_CUSTOMER");
        UserRoleModelId userRoleModelId = new UserRoleModelId(registeredUserModel.getId(), customerRoleModel.getId());
        UserRoleModel userRoleModel = new UserRoleModel();
        userRoleModel.setUserRoleModelId(userRoleModelId);
        userRoleRepository.save(userRoleModel);
    }
}
