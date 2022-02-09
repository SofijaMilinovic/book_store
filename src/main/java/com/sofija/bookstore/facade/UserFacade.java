package com.sofija.bookstore.facade;

import com.sofija.bookstore.data.UserData;
import com.sofija.bookstore.exception.UserException;
import com.sofija.bookstore.model.UserModel;
import com.sofija.bookstore.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserFacade {

    @Resource
    private UserService userService;

    public List<UserData> getAll() {
        return userService.getAll()
                .stream()
                .map(this::createUserData)
                .collect(Collectors.toList());
    }

    public UserData getById(int id) {
        UserModel userModel = userService.getById(id);
        return createUserData(userModel);
    }

    public UserData register(UserData userData) throws UserException {
        UserModel userModel = createUserModel(userData);
        UserModel registeredUserModel = userService.register(userModel);
        return createUserData(registeredUserModel);
    }

    public UserData login(UserData userData) throws UserException {
        UserModel loggedInUserModel = userService.login(userData.getEmail(), userData.getPassword());
        return createUserData(loggedInUserModel);
    }

    public boolean isAdmin(int userId) {
        return userService.isAdmin(userId);
    }

    public boolean isGoldenCustomer(int userId) {
        return userService.isGoldenCustomer(userId);
    }

    public UserData createUserData(UserModel userModel) {
        UserData userData = new UserData();
        userData.setId(userModel.getId());
        userData.setEmail(userModel.getEmail());
        userData.setFirstName(userModel.getFirstName());
        userData.setLastName(userModel.getLastName());
        return userData;
    }

    public UserModel createUserModel(UserData userData) {
        UserModel userModel = new UserModel();
        userModel.setId(userData.getId());
        userModel.setFirstName(userData.getFirstName());
        userModel.setLastName(userData.getLastName());
        userModel.setEmail(userData.getEmail());
        userModel.setPassword(userData.getPassword());
        return userModel;
    }
}
