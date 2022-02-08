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

    public UserData register(UserModel userModel) throws UserException {
        UserModel registeredUserModel = userService.register(userModel);
        return createUserData(registeredUserModel);
    }

    public UserData login(UserModel userModel) throws UserException {
        UserModel loggedInUserModel = userService.login(userModel);
        return createUserData(loggedInUserModel);
    }

    public UserData createUserData(UserModel userModel) {
        UserData userData = new UserData();
        userData.setId(userModel.getId());
        return userData;
    }
}
